package agilemeetings.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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
import agilemeetings.exceptions.SprintAsociadaException;
import agilemeetings.model.*;
import agilemeetings.model.propertyeditor.EstadoProyectoEditor;
import agilemeetings.model.propertyeditor.ProductBacklogItemEditor;
import agilemeetings.service.ProductBacklogService;
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
	@Autowired
	private ProductBacklogService productBacklogService;

	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(ProductBacklogItem.class, new ProductBacklogItemEditor(productBacklogService));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(EstadoProyecto.class, new EstadoProyectoEditor(proyectoService));
		binder.registerCustomEditor(List.class, "to_items", new CustomCollectionEditor(List.class)
				{
			protected Object convertElement(Object element) {
				if (element instanceof ItemSprint) {
					System.out.println("Converting from ItemSprint to ItemSprint: " + element);
					return element;
				}
				if (element instanceof String) {
					// Recibo id de product backlog. Si este item ya esta asignado a este
					// sprint, no hago nada. Si no, tengo que crearlo.
					ProductBacklogItem staff=productBacklogService.getBacklogItemById(Integer.parseInt((String) element));
					System.out.println("Looking up staff for id " + element + ": " + staff);
					ItemSprint i=new ItemSprint();
					i.setItem(staff);
					return i;
				}
				System.out.println("Don't know what to do with: " + element);
				return null;
			}
				}
				);
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
				redirectAttributes.addFlashAttribute("message","Sprint agregada exitosamente");
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
			ModelAndView modelo=this.cargarFormSprint("sprints_edit",sprint);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/sprints/listar/"+sprint.getProyecto().getId());
			//try
			//{
				sprintService.grabar(sprint);
				redirectAttributes.addFlashAttribute("message","Sprint editado exitosamente");
			//}
			//catch(ReunionExistenteException e)
			//{
				//model.addAttribute("message","Ese nombre de reunion ya existe, por favor elija otro");
				//modelo=this.cargarFormReunion("reuniones_edit",reunion);
			//}
			return modelo;
		}
	}
	@Descripcion(value="Borrar Sprint",permission="ROLE_SPRINT_DELETE")
	@RequestMapping(value="/delete/{sprintId}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINT_DELETE')")
	public ModelAndView confirmarBorradoSprint(@PathVariable("sprintId") Integer sprintId,
			ModelMap model)
	{
		// Muestra una pantalla de confirmacion.
		Sprint p=this.sprintService.getSprintById(sprintId);
		ModelAndView modelo=new ModelAndView("sprint_delete");
		modelo.addObject("sprint",p);
		return modelo;
	}
	@RequestMapping(value="/delete/{backlogId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINT_DELETE')")
	public ModelAndView borrarSprint(@PathVariable("sprintId") Integer sprintId,
			@Valid @ModelAttribute("sprint") Sprint sprint,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		ModelAndView modelo=new ModelAndView("redirect:/sprints/listar/"+sprint.getProyecto().getId());
		String message="Sprint borrado";
		String type="success";
		try 
		{
			sprintService.borrarSprint(sprint);
		} 
		catch (SprintAsociadaException e) 
		{
			e.printStackTrace();
			message="Este sprint tiene items de backlog asociados. Borrelos primero";
			type="error";
		}
		redirectAttributes.addFlashAttribute("message",message);
		redirectAttributes.addFlashAttribute("type",type);
		return modelo;
	}
	@Descripcion(value="Agregar items del product backlog a un Sprint",permission="ROLE_SPRINT_ABM")
	@RequestMapping(value="/backlog/{sprintId}",method=RequestMethod.GET)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINT_ABM')")
	public ModelAndView mostrarPantallaAsignacionProductBacklog(@PathVariable("sprintId") Integer sprintId)
	{
		Sprint s=sprintService.getSprintById(sprintId);
		ModelAndView m=new ModelAndView("sprints_backlog");
		m.addObject("sprint",s);
		// Tengo que pedir el product backlog de este proyecto.
		List<ProductBacklogItem> p=productBacklogService.listarProductBacklog(s.getProyecto());
		m.addObject("product_backlog",p);
		// Le mando mi url para que lo ponga incluido en el javascript que proceso!
		m.addObject("url","/sprints/backlog/"+sprintId);
		return m;
	}
	@RequestMapping(value="/backlog/{sprintId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_SPRINT_ABM')")
	public @ResponseBody ModelAndView procesarAsignacionProductBacklog(@PathVariable("sprintId") Integer sprintId,
			@Valid @ModelAttribute("sprint") Sprint sprint,
			@RequestParam("to_items") List<ProductBacklogItem> items,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		ModelAndView m=new ModelAndView("redirect:/sprints/listar/"+sprint.getProyecto().getId());
		log.trace("Recibi items");
		Iterator<ProductBacklogItem> i=items.iterator();
		while(i.hasNext())
		{
			ProductBacklogItem x=i.next();
			log.trace("id="+x.getId()+", value="+x.getTitulo());
		}
		return m;
	}
}
