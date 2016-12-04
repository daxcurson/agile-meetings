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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import agilemeetings.documentation.Descripcion;
import agilemeetings.documentation.DescripcionClase;
import agilemeetings.model.EstadoProyecto;
import agilemeetings.model.Proyecto;
import agilemeetings.model.Sprint;
import agilemeetings.model.propertyeditor.EstadoProyectoEditor;
import agilemeetings.service.ProyectoService;
import agilemeetings.service.SprintService;

@Controller
@RequestMapping("sprints")
@SessionAttributes("sprint")
@DescripcionClase("Sprints")
public class SprintsController extends AppController
{
	private static Logger log=LogManager.getLogger(SprintsController.class);
	@Autowired
	private SprintService sprintService;
	@Autowired
	private ProyectoService proyectoService;

	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(EstadoProyecto.class, new EstadoProyectoEditor(proyectoService));
	}

	@RequestMapping("/listar/{proyectoId}")
	@Descripcion(value="Listar sprints",permission="ROLE_SPRINTS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINTS_MOSTRAR_MENU')")
	public ModelAndView listarSprintsProyecto(@PathVariable("proyectoId") Integer proyectoId)
	{
		// Leo la lista de sprints de este proyecto
		ModelAndView modelo=new ModelAndView("sprints_index");
		Proyecto p=proyectoService.getProyectoById(proyectoId);
		modelo.addObject("proyecto",p);
		modelo.addObject("sprints",sprintService.listarSprints(p));
		return modelo;
	}
	private ModelAndView cargarFormSprint(String vista,Sprint sprint)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("sprint",sprint);
		modelo.addObject("estados",proyectoService.listarEstadosProyecto());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINTS_AGREGAR')")
	@RequestMapping(value="/{proyectoId}/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarSprint(
			@PathVariable("proyectoId") Integer proyectoId,
			Model model)
	{
		ModelAndView modelo=this.cargarFormSprint("sprints_add",new Sprint());
		return modelo;
	}
	@Descripcion(value="Agregar sprint",permission="ROLE_SPRINTS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINTS_AGREGAR')")
	@RequestMapping(value = "/{proyectoId}/add", method = RequestMethod.POST)
	public ModelAndView agregarSprint(
			@PathVariable("proyectoId") Integer proyectoId,
			@Valid @ModelAttribute("sprint")
	Sprint sprint,
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
			ModelAndView modelo=this.cargarFormSprint("sprints_add",sprint);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/sprints/listar/"+proyectoId);
			//try
			//{
				Proyecto p=proyectoService.getProyectoById(proyectoId);
				sprintService.agregar(sprint,p);
				model.addAttribute("message","Sprint agregada exitosamente");
			//}
			//catch(ReunionExistenteException e)
			//{
			//	model.addAttribute("message","Ese nombre de sprint ya existe, por favor elija otro");
			//	modelo=this.cargarFormSprint("sprint_add",sprint);
			//}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINTS_EDIT')")
	@RequestMapping(value="/edit/{sprintId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("sprintId") Integer sprintId,
			Model model)
	{
		// Busco el sprint
		Sprint sprint=sprintService.getSprintById(sprintId);
		ModelAndView modelo=this.cargarFormSprint("sprints_edit",sprint);
		return modelo;
	}
	@Descripcion(value="Editar Sprint",permission="ROLE_SPRINTS_EDIT")
	@RequestMapping(value="/edit/{sprintId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINTS_EDIT')")
	public ModelAndView editarSprint(@PathVariable("sprintId") Integer sprintId,
			@Valid @ModelAttribute("sprint") Sprint sprint,
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
			ModelAndView modelo=this.cargarFormSprint("sprints_edit",sprint);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/sprints/listar/"+sprint.getProyecto().getId());
			//try
			//{
				sprintService.grabar(sprint);
				model.addAttribute("message","Sprint editado exitosamente");
			//}
			//catch(ReunionExistenteException e)
			//{
				//model.addAttribute("message","Ese nombre de reunion ya existe, por favor elija otro");
				//modelo=this.cargarFormReunion("reuniones_edit",reunion);
			//}
			return modelo;
		}
	}
}
