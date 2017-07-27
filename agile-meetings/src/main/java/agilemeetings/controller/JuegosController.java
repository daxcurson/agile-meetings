package agilemeetings.controller;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import agilemeetings.controller.juegos.JuegoHelper;
import agilemeetings.controller.juegos.StrategyFactory;
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
	//private static Logger log=LogManager.getLogger(JuegosController.class);
	@Autowired
	private JuegoService juegoService;
	@Autowired
	private StrategyFactory strategyFactory;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(TipoJuego.class, new TipoJuegoEditor(juegoService));
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_JUEGOS_PARTICIPAR')")
	@Descripcion(value="Participar en un juego",permission="ROLE_JUEGOS_PARTICIPAR")
	@RequestMapping(value="/participar/{juegoId}",method=RequestMethod.GET)
	public ModelAndView participarEnJuego(@PathVariable("juegoId") int juegoId)
	{
		// De acuerdo al juego leido, realizo acciones.
		// Uso una estrategia para llamar a un Helper que me va a dar la pantalla
		// principal del juego, segun el tipo de juego que este tenga cargado.
		Juego j=juegoService.getJuegoById(juegoId);
		ModelAndView v=new ModelAndView(j.getTipo_juego().getVista());
		JuegoHelper jh=strategyFactory.getStrategy(JuegoHelper.class, j.getTipo_juego().getCodigoEnum());
		v=jh.proveerModelo(v,juegoId);
		v.addObject("juego",j);
		return v;
	}
}
