package agilemeetings.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.EstadoBacklogItemDAO;
import agilemeetings.dao.ProductBacklogDAO;
import agilemeetings.dao.ProyectoDAO;
import agilemeetings.model.EstadoBacklogItem;
import agilemeetings.model.ProductBacklogItem;
import agilemeetings.model.Proyecto;
import agilemeetings.service.ProductBacklogService;

@Service
public class ProductBacklogServiceImpl implements ProductBacklogService
{
	@Autowired
	private ProductBacklogDAO productBacklogDAO;
	@Autowired
	private EstadoBacklogItemDAO estadoBacklogItemDAO;
	@Autowired
	private ProyectoDAO proyectoDAO;

	@Override
	public List<ProductBacklogItem> listarProductBacklog(Proyecto p) 
	{
		return productBacklogDAO.getItems(p.getId());
	}
	@Override
	public List<EstadoBacklogItem> getEstados()
	{
		return estadoBacklogItemDAO.getEstados();
	}
	@Override
	public void agregar(ProductBacklogItem backlogItem,Integer proyectoId) 
	{
		Proyecto p=proyectoDAO.getProyectoById(proyectoId);
		backlogItem.setFecha_creacion(new Date());
		backlogItem.setProyecto(p);
		productBacklogDAO.save(backlogItem);
	}
	@Override
	public EstadoBacklogItem getEstadoById(int id) 
	{
		return estadoBacklogItemDAO.getEstadoById(id);
	}
	@Override
	public ProductBacklogItem getBacklogItemById(Integer backlogId) 
	{
		return productBacklogDAO.getItemById(backlogId);
	}
	@Override
	public void grabarBacklogItem(ProductBacklogItem backlogItem) 
	{
		productBacklogDAO.update(backlogItem);
	}
	@Override
	@Transactional
	public void borrarBacklogItem(ProductBacklogItem backlogItem) 
	{
		productBacklogDAO.delete(backlogItem);
	}
}
