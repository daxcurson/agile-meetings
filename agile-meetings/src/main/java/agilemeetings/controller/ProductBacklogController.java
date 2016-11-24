package agilemeetings.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import agilemeetings.documentation.Descripcion;
import agilemeetings.documentation.DescripcionClase;
import agilemeetings.service.ProductBacklogService;

@Controller
@RequestMapping("productbacklog")
@SessionAttributes("backlog")
@DescripcionClase("Product Backlog")
public class ProductBacklogController extends AppController
{
	@SuppressWarnings("unused")
	private static Logger log=LogManager.getLogger(ProductBacklogController.class);
	@Autowired
	private ProductBacklogService backlogService;
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Listar items del Product Backlog",permission="ROLE_BACKLOG_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_BACKLOG_MOSTRAR_MENU')")
	public ModelAndView mostrarMenu()
	{
		// Leo las reuniones que hubieron para que aparezcan
		// en la pantalla indice.
		ModelAndView modelo=new ModelAndView("backlog_index");
		modelo.addObject("backlog_items",backlogService.listarProductBacklog(null));
		return modelo;
	}
}
