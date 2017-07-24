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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agilemeetings.documentation.Descripcion;
import agilemeetings.documentation.DescripcionClase;
import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.EstadoJuego;
import agilemeetings.model.Persona;
import agilemeetings.model.PersonaReunion;
import agilemeetings.model.Proyecto;
import agilemeetings.model.Reunion;
import agilemeetings.model.TipoJuego;
import agilemeetings.model.TipoReunion;
import agilemeetings.model.propertyeditor.EstadoJuegoEditor;
import agilemeetings.model.propertyeditor.PersonaReunionEditor;
import agilemeetings.model.propertyeditor.ProyectoEditor;
import agilemeetings.model.propertyeditor.TipoJuegoEditor;
import agilemeetings.model.propertyeditor.TipoReunionEditor;
import agilemeetings.service.JuegoService;
import agilemeetings.service.PersonaService;
import agilemeetings.service.ProyectoService;
import agilemeetings.service.ReunionService;
import agilemeetings.service.UserDetails;

@Controller
@RequestMapping("reuniones")
@SessionAttributes("reunion")
@DescripcionClase("Reuniones")
public class ReunionesController extends AppController
{
	private static Logger log=LogManager.getLogger(ReunionesController.class);
	@Autowired
	private ReunionService reunionService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private JuegoService juegoService;
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Proyecto.class, new ProyectoEditor(proyectoService));
		binder.registerCustomEditor(TipoReunion.class, new TipoReunionEditor(reunionService));
		binder.registerCustomEditor(PersonaReunion.class, new PersonaReunionEditor(personaService));
		binder.registerCustomEditor(TipoJuego.class, new TipoJuegoEditor(juegoService));
		binder.registerCustomEditor(EstadoJuego.class, new EstadoJuegoEditor(juegoService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Listar reuniones",permission="ROLE_REUNIONES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		// Leo las reuniones que hubieron para que aparezcan
		// en la pantalla indice.
		ModelAndView modelo=new ModelAndView("reuniones_index");
		modelo.addObject("reuniones",reunionService.listarReuniones());
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_MOSTRAR_MENU')")
	@RequestMapping(value="/listar/{proyectoId}",method=RequestMethod.GET)
	public ModelAndView listarReunionesProyecto(@PathVariable("proyectoId") Integer proyectoId)
	{
		// Leo las reuniones que hubieron para que aparezcan
		// en la pantalla indice.
		ModelAndView modelo=new ModelAndView("reuniones_index");
		modelo.addObject("reuniones",reunionService.listarReunionesProyecto(proyectoId));
		return modelo;
	}
	private ModelAndView cargarFormReunion(String vista,Reunion reunion)
	{
		ModelAndView modelo=new ModelAndView(vista);
		List<Proyecto> proyectos=proyectoService.listarProyectos();
		List<TipoReunion> tipos_reunion=reunionService.listarTiposReunion();
		modelo.addObject("proyectos",proyectos);
		modelo.addObject("tipos_reunion",tipos_reunion);
		modelo.addObject("reunion",reunion);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarReunion(Model model)
	{
		ModelAndView modelo=this.cargarFormReunion("reuniones_add",new Reunion());
		return modelo;
	}
	@Descripcion(value="Agregar reunion",permission="ROLE_REUNIONES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarReunion(@Valid @ModelAttribute("reunion")
	Reunion reunion,
	BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			model.addAttribute("message","Hay errores en el formulario");
			model.addAttribute("type","danger");
			ModelAndView modelo=this.cargarFormReunion("reuniones_add",reunion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/reuniones/index");
			try
			{
				reunionService.agregar(reunion);
				redirectAttributes.addFlashAttribute("message","Reunion agregada exitosamente");
			}
			catch(ReunionExistenteException e)
			{
				model.addAttribute("message","Hay errores en el formulario");
				model.addAttribute("type","danger");
				modelo=this.cargarFormReunion("reuniones_add",reunion);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_EDIT')")
	@RequestMapping(value="/edit/{reunionId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("reunionId") Integer reunionId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		Reunion reunion=reunionService.getReunionById(reunionId);
		ModelAndView modelo=this.cargarFormReunion("reuniones_edit",reunion);
		return modelo;
	}
	@Descripcion(value="Editar Reunion",permission="ROLE_REUNIONES_EDIT")
	@RequestMapping(value="/edit/{reunionId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_EDIT')")
	public ModelAndView editarReunion(@PathVariable("proyectoId") Integer proyectoId,
			@Valid @ModelAttribute("reunion") Reunion reunion,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormReunion("reuniones_edit",reunion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/proyectos/index");
			try
			{
				reunionService.grabar(reunion);
				redirectAttributes.addFlashAttribute("message","Proyecto editado exitosamente");
			}
			catch(ReunionExistenteException e)
			{
				model.addAttribute("message","Ese nombre de reunion ya existe, por favor elija otro");
				modelo=this.cargarFormReunion("reuniones_edit",reunion);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_DELETE')")
	@Descripcion(value="Borrar Reunion",permission="ROLE_REUNIONES_DELETE")
	@RequestMapping(value="/delete/{reunionId}",method=RequestMethod.GET)
	public ModelAndView borrarReunion(@PathVariable("reunionId") Integer reunionId)
	{
		ModelAndView modelo=new ModelAndView("redirect:/reuniones/index");
		reunionService.borrar(reunionId);
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_VIEW')")
	@Descripcion(value="Ver Reunion",permission="ROLE_REUNIONES_VIEW")
	@RequestMapping(value="/view/{reunionId}",method=RequestMethod.GET)
	public ModelAndView verReunion(@PathVariable("reunionId") Integer reunionId)
	{
		ModelAndView modelo=new ModelAndView("reuniones_view");
		modelo.addObject("reunion",reunionService.getReunionById(reunionId));
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_PARTICIPAR')")
	@Descripcion(value="Participar en una reunion",permission="ROLE_REUNIONES_PARTICIPAR")
	@RequestMapping(value="/participar/{reunionId}",method=RequestMethod.GET)
	public ModelAndView participarEnReunion(@PathVariable("reunionId") Integer reunionId)
	{
		ModelAndView modelo=new ModelAndView("reuniones_participar");
		modelo.addObject("reunion",reunionService.getReunionById(reunionId));
		return modelo;
	}
	@RequestMapping("/mis_reuniones")
	@Descripcion(value="Mostrar la lista de reuniones donde participa la persona",permission="ROLE_REUNIONES_MIS_REUNIONES")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_MIS_REUNIONES')")
	public ModelAndView mostrarListaReuniones()
	{
		ModelAndView modelo=new ModelAndView("reuniones_mis_reuniones");
		// Busco el usuario que esta registrado en el sistema.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails u=(UserDetails) auth.getPrincipal();
		Persona p=u.getPersona();
		modelo.addObject("mis_reuniones",reunionService.listarReunionesParticipadasPersona(p.getId()));
		return modelo;
	}
	@RequestMapping("/agregar_juego/{reunionId}")
	@Descripcion(value="Agregar juego a la reunion",permission="ROLE_REUNIONES_AGREGAR_JUEGO")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_REUNIONES_AGREGAR_JUEGO')")
	public ModelAndView agregarJuego(@PathVariable("reunionId") Integer reunionId)
	{
		ModelAndView modelo=new ModelAndView("reuniones_agregar_juego");
		modelo.addObject("reunion",this.reunionService.getReunionById(reunionId));
		modelo.addObject("tipos_juego",juegoService.listarTiposJuego());
		return modelo;
	}
}
