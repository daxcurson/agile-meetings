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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agilemeetings.documentation.Descripcion;
import agilemeetings.documentation.DescripcionClase;
import agilemeetings.model.Juego;
import agilemeetings.model.TipoJuego;
import agilemeetings.model.propertyeditor.TipoJuegoEditor;
import agilemeetings.service.JuegoService;

@Controller
@RequestMapping(value="juegos")
@SessionAttributes("juego")
@DescripcionClase("Juegos")
public class JuegosController extends AppController
{
	private static Logger log=LogManager.getLogger(JuegosController.class);
	@Autowired
	private JuegoService juegoService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(TipoJuego.class, new TipoJuegoEditor(juegoService));
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_JUEGO_AGREGAR_REUNION')")
	@RequestMapping(value="/add/{reunionId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarJuego(@PathVariable("reunionId") int reunionId,Model model)
	{
		ModelAndView formJuego=this.cargarFormJuego("juego_add_reunion",new Juego());
		return formJuego;
	}
	private ModelAndView cargarFormJuego(String vista, Juego juego) 
	{
		ModelAndView v=new ModelAndView(vista);
		v.addObject("juego",juego);
		return v;
	}
	@Descripcion(value="Agregar juego en reunion",permission="ROLE_JUEGO_AGREGAR_REUNION")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_JUEGO_AGREGAR_REUNION')")
	@RequestMapping(value = "/add/{reunionId}", method = RequestMethod.POST)
	public ModelAndView agregarJuego(
			@PathVariable("reunionId") Integer reunionId,
			@Valid @ModelAttribute("juego")
	Juego juego,
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
			ModelAndView modelo=this.cargarFormJuego("juego_add_reunion",juego);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/reunion/view/"+reunionId);
			try
			{
				juegoService.agregar(juego,reunionId);
				redirectAttributes.addFlashAttribute("message","Juego agregado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar el juego");
				modelo=this.cargarFormJuego("juego_add_reunion",juego);
			}
			return modelo;
		}
	}
}
