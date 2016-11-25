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
import agilemeetings.exceptions.PersonaExistenteException;
import agilemeetings.model.Group;
import agilemeetings.model.Persona;
import agilemeetings.model.propertyeditor.GroupEditor;
import agilemeetings.service.GroupService;
import agilemeetings.service.PersonaService;

@Controller
@RequestMapping(value="personas")
@SessionAttributes("persona")
@DescripcionClase("Personas")
public class PersonasController extends AppController
{
	private static Logger log=LogManager.getLogger(PersonasController.class);

	@Autowired
	private PersonaService personaService;
	@Autowired
	private GroupService groupService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(Group.class, new GroupEditor(groupService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de personas y menu",permission="ROLE_PERSONAS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PERSONAS_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		ModelAndView modelo=new ModelAndView("personas_index");
		// Leemos los usuarios que hay.
		modelo.addObject("personas",personaService.listarPersonas());
		return modelo;
	}
	
	private ModelAndView cargarFormPersona(String vista,Persona persona)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("persona",persona);
		modelo.addObject("groups",groupService.listGroups());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PERSONAS_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarPersona(Model model)
	{
		ModelAndView modelo=this.cargarFormPersona("personas_add",new Persona());
		return modelo;
	}
	@Descripcion(value="Agregar Persona",permission="ROLE_PERSONAS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PERSONAS_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarPersona(@Valid @ModelAttribute("persona")
	Persona persona,
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
			ModelAndView modelo=this.cargarFormPersona("personas_add",persona);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/personas/index");
			try
			{
				personaService.agregarPersona(persona);
				model.addAttribute("message","Persona agregado exitosamente");
			}
			catch(PersonaExistenteException e)
			{
				model.addAttribute("message","Ese nombre de persona ya existe, por favor elija otro");
				modelo=this.cargarFormPersona("personas_add",persona);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PERSONAS_EDIT')")
	@RequestMapping(value="/edit/{personaId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("personaId") Integer personaId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		Persona persona=personaService.getPersonaById(personaId);
		ModelAndView modelo=this.cargarFormPersona("personas_edit",persona);
		return modelo;
	}
	@Descripcion(value="Editar Persona",permission="ROLE_PERSONAS_EDIT")
	@RequestMapping(value="/edit/{personaId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PERSONAS_EDIT')")
	public ModelAndView editarPersona(@PathVariable("personaId") Integer personaId,
			@Valid @ModelAttribute("persona") Persona persona,
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
			ModelAndView modelo=this.cargarFormPersona("personas_edit",persona);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/personas/index");
			try
			{
				personaService.grabarPersona(persona);
				model.addAttribute("message","Persona editada exitosamente");
			}
			catch(PersonaExistenteException e)
			{
				model.addAttribute("message","Ese nombre de persona ya existe, por favor elija otro");
				modelo=this.cargarFormPersona("personas_edit",persona);
			}
			return modelo;
		}
	}
}
