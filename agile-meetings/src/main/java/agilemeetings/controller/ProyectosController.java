package agilemeetings.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import agilemeetings.documentation.DescripcionClase;
import agilemeetings.service.ReunionService;
import agilemeetings.service.UserDetailsService;

@Controller
@RequestMapping("proyectos")
@SessionAttributes("proyecto")
@DescripcionClase("Proyectos")
public class ProyectosController extends AppController 
{
	static Logger log = Logger.getLogger(ReunionesController.class);
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private ReunionService reunionService;
}
