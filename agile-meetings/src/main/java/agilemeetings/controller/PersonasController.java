package agilemeetings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import agilemeetings.documentation.DescripcionClase;

@Controller
@RequestMapping(value="personas")
@DescripcionClase("Personas")
public class PersonasController extends AppController
{
	
}
