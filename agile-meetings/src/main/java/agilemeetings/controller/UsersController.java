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

import agilemeetings.documentation.DescripcionClase;
import agilemeetings.exceptions.UsuarioExistenteException;
import agilemeetings.model.User;
import agilemeetings.service.GroupService;
import agilemeetings.service.UserDetailsService;
import agilemeetings.documentation.Descripcion;

@Controller
@RequestMapping("users")
@SessionAttributes("user")
@DescripcionClase("Usuarios")
public class UsersController extends AppController
{
	static Logger log = Logger.getLogger(UsersController.class);
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private GroupService groupService;

	@RequestMapping({"/","/index"})
	@Descripcion(value="Listar usuarios",permission="ROLE_USERS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USERS_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		return new ModelAndView("users_index");
	}

	@RequestMapping("/login")
	public ModelAndView login(Model model) 
	{
		ModelAndView modelo=new ModelAndView("login");
		return modelo;
	}
	private ModelAndView cargarFormUsuario(User user)
	{
		ModelAndView modelo=new ModelAndView("users_add");
		modelo.addObject("user",user);
		modelo.addObject("groups",groupService.listGroups());
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USERS_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregar(Model model)
	{
		ModelAndView modelo=this.cargarFormUsuario(new User());
		return modelo;
	}
	@Descripcion(value="Agregar usuario",permission="ROLE_USERS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USERS_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("user")
	User user,
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
			ModelAndView modelo=this.cargarFormUsuario(user);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/users/index");
			try
			{
				userService.save(user);
				model.addAttribute("message","Usuario agregado exitosamente");
			}
			catch(UsuarioExistenteException e)
			{
				model.addAttribute("message","Ese nombre de usuario ya existe, por favor elija otro");
				modelo=this.cargarFormUsuario(user);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USERS_EDIT')")
	@RequestMapping(value="/edit/{userId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("userId") Integer userId,
			Model model)
	{
		// Busco el usuario y lo cargo en el formulario.
		User user=userService.getById(userId);
		ModelAndView modelo=this.cargarFormUsuario(user);
		return modelo;
	}
	@Descripcion(value="Editar Usuario",permission="ROLE_USERS_EDIT")
	@RequestMapping(value="/edit/{userId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USERS_EDIT')")
	public ModelAndView editarUser(@PathVariable("userId") Integer userId,
			@Valid @ModelAttribute("user") User user,
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
			ModelAndView modelo=this.cargarFormUsuario(user);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/users/index");
			try
			{
				userService.save(user);
				model.addAttribute("message","Usuario editado exitosamente");
			}
			catch(UsuarioExistenteException e)
			{
				model.addAttribute("message","Ese nombre de usuario ya existe, por favor elija otro");
				modelo=this.cargarFormUsuario(user);
			}
			return modelo;
		}
	}
	@RequestMapping("/logindenied")
	public ModelAndView loginDenied(Model model)
	{
		ModelAndView modelo=new ModelAndView("login_denied");
		return modelo;
	}
}
