package agilemeetings.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
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
import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.Reunion;
import agilemeetings.service.ReunionService;

@Controller
@RequestMapping("reuniones")
@SessionAttributes("reunion")
@DescripcionClase("Reuniones")
public class ReunionesController extends AppController
{
	private static Logger log=LogManager.getLogger(ReunionesController.class);
	@Autowired
	private ReunionService reunionService;
	
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
	private ModelAndView cargarFormReunion(String vista,Reunion reunion)
	{
		ModelAndView modelo=new ModelAndView(vista);
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
			ModelAndView modelo=this.cargarFormReunion("reuniones_add",reunion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/reuniones/index");
			try
			{
				reunionService.agregar(reunion);
				model.addAttribute("message","Reunion agregada exitosamente");
			}
			catch(ReunionExistenteException e)
			{
				model.addAttribute("message","Ese nombre de reunion ya existe, por favor elija otro");
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
			ModelAndView modelo=this.cargarFormReunion("reuniones_edit",reunion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/proyectos/index");
			try
			{
				reunionService.grabar(reunion);
				model.addAttribute("message","Proyecto editado exitosamente");
			}
			catch(ReunionExistenteException e)
			{
				model.addAttribute("message","Ese nombre de reunion ya existe, por favor elija otro");
				modelo=this.cargarFormReunion("reuniones_edit",reunion);
			}
			return modelo;
		}
	}
}
