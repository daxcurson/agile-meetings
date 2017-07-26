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

import agilemeetings.documentation.DescripcionClase;
import agilemeetings.model.MadSadGlad;
import agilemeetings.model.Tarjeta;
import agilemeetings.model.TipoJuego;
import agilemeetings.model.propertyeditor.TipoJuegoEditor;
import agilemeetings.service.JuegoService;

@Controller
@RequestMapping(value="mad_sad_glad")
@SessionAttributes({"juego","tarjeta"})
@DescripcionClase("Juego: Mad, Sad, Glad")
public class MadSadGladController extends AppController
{
	private static Logger log=LogManager.getLogger(MadSadGladController.class);
	@Autowired
	private JuegoService juegoService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(TipoJuego.class, new TipoJuegoEditor(juegoService));
	}
	private ModelAndView cargarFormTarjeta(String vista,Tarjeta tarjeta)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("tarjeta",tarjeta);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_JUEGOS_PARTICIPAR')")
	@RequestMapping(value="/agregar_tarjeta/{juegoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormAgregarTarjeta(@PathVariable("juegoId") int juegoId,Model model)
	{
		ModelAndView modelo=this.cargarFormTarjeta("juego_mad_sad_glad_agregar_tarjeta",new Tarjeta());
		modelo.addObject("juego",juegoService.getJuegoById(juegoId));
		return modelo;
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_JUEGOS_PARTICIPAR')")
	@RequestMapping(value = "/agregar_tarjeta/{juegoId}", method = RequestMethod.POST)
	public ModelAndView agregarTarjeta(@PathVariable("juegoId") int juegoId,
			@Valid @ModelAttribute("tarjeta")
	Tarjeta tarjeta,
	@ModelAttribute("juego") MadSadGlad juego,
	BindingResult result,ModelMap model, final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormTarjeta("juego_mad_sad_glad_agregar_tarjeta",tarjeta);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/juegos/participar/"+juego.getId());
			try
			{
				juegoService.agregarTarjeta(tarjeta,juego);
				redirectAttributes.addFlashAttribute("message","Tarjeta agregada exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Algo horrible ha ocurrido...");
				log.trace("Error: "+e.getMessage());
				modelo=this.cargarFormTarjeta("juego_mad_sad_glad_agregar_tarjeta",tarjeta);
			}
			return modelo;
		}
	}
}
