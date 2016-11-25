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
import agilemeetings.model.EstadoBacklogItem;
import agilemeetings.model.ProductBacklogItem;
import agilemeetings.model.Proyecto;
import agilemeetings.model.propertyeditor.EstadoBacklogItemEditor;
import agilemeetings.service.ProductBacklogService;
import agilemeetings.service.ProyectoService;

@Controller
@RequestMapping("backlog")
@SessionAttributes("backlog_item")
@DescripcionClase("Product Backlog")
public class ProductBacklogController extends AppController
{
	private static Logger log=LogManager.getLogger(ProductBacklogController.class);
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private ProductBacklogService backlogService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(EstadoBacklogItem.class, new EstadoBacklogItemEditor(backlogService));
	}
	
	@RequestMapping("/listar/{proyectoId}")
	@Descripcion(value="Listar items del Product Backlog",permission="ROLE_BACKLOG_LISTAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BACKLOG_LISTAR')")
	public ModelAndView listarProductBacklog(@PathVariable("proyectoId") Integer proyectoId)
	{
		ModelAndView modelo=new ModelAndView("backlog_listar");
		// Para el proyecto pedido, voy a buscar el product backlog.
		try
		{
			Proyecto p=proyectoService.getProyectoById(proyectoId);
			modelo.addObject("proyecto",p);
			modelo.addObject("backlog_items",backlogService.listarProductBacklog(p));
		}
		catch(Exception e)
		{
			log.trace("Error al listar el backlog");
			log.trace(e.getMessage());
		}
		return modelo;
	}
	
	private ModelAndView cargarFormBacklogItem(String vista,ProductBacklogItem item)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("backlog_item",item);
		modelo.addObject("estados",backlogService.getEstados());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BACKLOG_AGREGAR')")
	@RequestMapping(value="/{proyectoId}/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarBacklogItem(
			@PathVariable("proyectoId") Integer proyectoId,
			Model model)
	{
		ModelAndView modelo=this.cargarFormBacklogItem("backlog_add",new ProductBacklogItem());
		return modelo;
	}
	@Descripcion(value="Agregar Item al ProductBacklog",permission="ROLE_BACKLOG_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BACKLOG_AGREGAR')")
	@RequestMapping(value = "/{proyectoId}/add", method = RequestMethod.POST)
	public ModelAndView agregarProyecto(
			@PathVariable("proyectoId") Integer proyectoId,
			@Valid @ModelAttribute("backlog_item")
	ProductBacklogItem backlogItem,
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
			ModelAndView modelo=this.cargarFormBacklogItem("backlog_add",backlogItem);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/backlog/listar/"+proyectoId);
			try
			{
				backlogService.agregar(backlogItem,proyectoId);
				model.addAttribute("message","Item agregado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar el item");
				modelo=this.cargarFormBacklogItem("backlog_add",backlogItem);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BACKLOG_EDIT')")
	@RequestMapping(value="/edit/{backlogId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("backlogId") Integer backlogId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		ProductBacklogItem p=this.backlogService.getBacklogItemById(backlogId);
		ModelAndView modelo=this.cargarFormBacklogItem("backlog_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Backlog Item",permission="ROLE_BACKLOG_EDIT")
	@RequestMapping(value="/edit/{backlogId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BACKLOG_EDIT')")
	public ModelAndView editarBacklogItem(@PathVariable("backlogId") Integer backlogId,
			@Valid @ModelAttribute("backlog_item") ProductBacklogItem backlogItem,
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
			ModelAndView modelo=this.cargarFormBacklogItem("backlog_edit",backlogItem);
			return modelo;
		}
		else
		{
			log.trace("El id del backlogItem es "+backlogItem.getId());
			ModelAndView modelo=new ModelAndView("redirect:/backlog/listar/"+backlogItem.getProyecto().getId());
			try
			{
				log.trace("Voy a grabar");
				backlogService.grabarBacklogItem(backlogItem);
				log.trace("Listo, grabe");
				model.addAttribute("message","Item editado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Error al editar Backlog Item");
				modelo=this.cargarFormBacklogItem("backlog_edit",backlogItem);
			}
			return modelo;
		}
	}
}
