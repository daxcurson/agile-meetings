package agilemeetings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import agilemeetings.documentation.DescripcionClase;
import agilemeetings.model.TipoJuego;
import agilemeetings.model.propertyeditor.TipoJuegoEditor;
import agilemeetings.service.JuegoService;

@Controller
@RequestMapping(value="mad_sad_glad")
@SessionAttributes("juego")
@DescripcionClase("Juego: Mad, Sad, Glad")
public class MadSadGladController extends AppController
{
	//private static Logger log=LogManager.getLogger(JuegosController.class);
	@Autowired
	private JuegoService juegoService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(TipoJuego.class, new TipoJuegoEditor(juegoService));
	}
	
}
