package agilemeetings.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import agilemeetings.documentation.Descripcion;
import agilemeetings.documentation.DescripcionClase;
import agilemeetings.exceptions.ProyectoExistenteException;
import agilemeetings.model.Proyecto;
import agilemeetings.service.ProyectoService;
import agilemeetings.service.UserDetailsService;

@Controller
@RequestMapping("proyectos")
@SessionAttributes("proyecto")
@DescripcionClase("Proyectos")
public class ProyectosController extends AppController 
{
	static Logger log = Logger.getLogger(ReunionesController.class);
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private ProyectoService proyectoService;
	
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
	BindingResult result,ModelMap model)
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
				model.addAttribute("message","Proyecto agregado exitosamente");
			}
			catch(ProyectoExistenteException e)
			{
				model.addAttribute("message","Ese nombre de proyecto ya existe, por favor elija otro");
				modelo=this.cargarFormProyecto("proyectos_add",proyecto);
			}
			return modelo;
		}
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
			BindingResult result,ModelMap model)
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
				model.addAttribute("message","Proyecto editado exitosamente");
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
