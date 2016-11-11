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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import agilemeetings.documentation.DescripcionClase;
import agilemeetings.model.User;
import agilemeetings.service.UserDetailsService;
import agilemeetings.documentation.Descripcion;

@Controller
@RequestMapping("users")
@DescripcionClase("Usuarios")
public class UsersController extends AppController
{
	static Logger log = Logger.getLogger(UsersController.class);
	@Autowired
	private UserDetailsService userService;

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
		ModelAndView modelo=new ModelAndView("user_add");
		modelo.addObject("user",user);
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
		// Las modalidades venian separadas, tengo que ir e incorporarlas
		// al curso que me acabo de traer.
		//curso.setModalidades_disponibles(listaModalidades);
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
			userService.save(user);
			model.addAttribute("message","Usuario agregado exitosamente");
			return new ModelAndView("usuario_index");
		}
	}
	@RequestMapping("/logindenied")
	public ModelAndView loginDenied(Model model)
	{
		ModelAndView modelo=new ModelAndView("login_denied");
		return modelo;
	}
}
