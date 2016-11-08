package agilemeetings.controller;

import agilemeetings.documentation.*;
import agilemeetings.service.ConfiguracionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="configuracion")
@DescripcionClase("Configuracion del sistema")
public class ConfiguracionController extends AppController 
{
	static Logger log = Logger.getLogger(ConfiguracionController.class);

	@Autowired
	private ConfiguracionService configuracionService;
	@PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_CONFIGURACION_MOSTRAR_MENU'))")
	@RequestMapping("/index")
	@Descripcion(value="Mostrar opciones configurables",permission="ROLE_CONFIGURACION_MOSTRAR_MENU")
	public String menuConfiguracion()
	{
		return "configuracion_index";
	}
}
