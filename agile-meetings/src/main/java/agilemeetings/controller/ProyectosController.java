package agilemeetings.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agilemeetings.documentation.Descripcion;
import agilemeetings.documentation.DescripcionClase;
import agilemeetings.exceptions.ProyectoExistenteException;
import agilemeetings.model.EstadoProyecto;
import agilemeetings.model.Proyecto;
import agilemeetings.model.RolPersona;
import agilemeetings.model.propertyeditor.EstadoProyectoEditor;
import agilemeetings.service.PersonaService;
import agilemeetings.service.ProyectoService;
import agilemeetings.service.RolService;

@Controller
@RequestMapping("proyectos")
@SessionAttributes("proyecto")
@DescripcionClase("Proyectos")
public class ProyectosController extends AppController 
{
	private static Logger log=LogManager.getLogger(ProyectosController.class);
	
	@Autowired
	private PersonaService personaService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private RolService rolService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(EstadoProyecto.class, new EstadoProyectoEditor(proyectoService));
	}

	@RequestMapping({"/","/index"})
	@Descripcion(value="Listar proyectos",permission="ROLE_PROYECTOS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		// Leo las reuniones que hubieron para que aparezcan
		// en la pantalla indice.
		ModelAndView modelo=new ModelAndView("proyectos_index");
		modelo.addObject("proyectos",proyectoService.listarProyectos());
		return modelo;
	}
	
	private ModelAndView cargarFormProyecto(String vista,Proyecto proyecto)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("proyecto",proyecto);
		modelo.addObject("personas",personaService.listarPersonas());
		modelo.addObject("estados",proyectoService.listarEstadosProyecto());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarProyecto(Model model)
	{
		ModelAndView modelo=this.cargarFormProyecto("proyectos_add",new Proyecto());
		return modelo;
	}
	@Descripcion(value="Agregar proyecto",permission="ROLE_PROYECTOS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarProyecto(@Valid @ModelAttribute("proyecto")
	Proyecto proyecto,
	BindingResult result,ModelMap model, final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormProyecto("proyectos_add",proyecto);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/proyectos/index");
			try
			{
				proyectoService.agregar(proyecto);
				redirectAttributes.addFlashAttribute("message","Proyecto agregado exitosamente");
			}
			catch(ProyectoExistenteException e)
			{
				model.addAttribute("message","Ese nombre de proyecto ya existe, por favor elija otro");
				modelo=this.cargarFormProyecto("proyectos_add",proyecto);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_AGREGAR')")
	@RequestMapping(value = "/agregar_miembro")
	public @ResponseBody List<RolPersona> agregarMiembro(
			@RequestParam(value="persona_id",required=true) Integer persona_id,
			@ModelAttribute("proyecto") Proyecto proyecto
			)
	{
		// Si la persona ya existe como miembro del proyecto, ignorar este comando!!
		List<RolPersona> miembros=proyecto.getMiembros();
		boolean encontrado=false;
		Iterator<RolPersona> iterator=miembros.iterator();
		while(iterator.hasNext() && !encontrado)
		{
			RolPersona p=iterator.next();
			if(p.getId()==persona_id)
				encontrado=true;
		}
		if(!encontrado)
		{
			RolPersona r=new RolPersona();
			r.setPersona(personaService.getPersonaById(persona_id));
			r.setProyecto(proyecto);
			r.setRol(rolService.getRolById(1));
			proyecto.getMiembros().add(r);
		}
		return proyecto.getMiembros();
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_AGREGAR')")
	@RequestMapping(value = "/quitar_miembro")
	public @ResponseBody List<RolPersona> quitarMiembro(
			@RequestParam(value="rol_id",required=true) Integer rol_id,
			@ModelAttribute("proyecto") Proyecto proyecto
			)
	{
		List<RolPersona> lista=proyecto.getMiembros();
		// Vamos a excluir a los miembros del proyecto sabiendo sus id de usuarios,
		// informados en rol_id.
		Iterator<RolPersona> iterator=lista.iterator();
		while(iterator.hasNext())
		{
			RolPersona i=iterator.next();
			if(i.getPersona().getId()==rol_id)
				iterator.remove();
		}
		return proyecto.getMiembros();		
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_EDIT')")
	@RequestMapping(value="/edit/{proyectoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("proyectoId") Integer proyectoId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		Proyecto proyecto=proyectoService.getProyectoById(proyectoId);
		ModelAndView modelo=this.cargarFormProyecto("proyectos_edit",proyecto);
		return modelo;
	}
	@Descripcion(value="Editar Proyecto",permission="ROLE_PROYECTOS_EDIT")
	@RequestMapping(value="/edit/{proyectoId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_EDIT')")
	public ModelAndView editarProyecto(@PathVariable("proyectoId") Integer proyectoId,
			@Valid @ModelAttribute("proyecto") Proyecto proyecto,
			BindingResult result,ModelMap model,
			final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormProyecto("proyectos_edit",proyecto);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/proyectos/index");
			try
			{
				proyectoService.grabar(proyecto);
				redirectAttributes.addFlashAttribute("message","Proyecto editado exitosamente");
			}
			catch(ProyectoExistenteException e)
			{
				model.addAttribute("message","Ese nombre de proyecto ya existe, por favor elija otro");
				modelo=this.cargarFormProyecto("proyectos_edit",proyecto);
			}
			return modelo;
		}
	}
}
