package agilemeetings.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.SprintDAO;
import agilemeetings.exceptions.SprintAsociadaException;
import agilemeetings.model.ItemSprint;
import agilemeetings.model.ProductBacklogItem;
import agilemeetings.model.Proyecto;
import agilemeetings.model.Sprint;
import agilemeetings.service.SprintService;

@Service
public class SprintServiceImpl implements SprintService
{
	private static Logger log=LogManager.getLogger(SprintServiceImpl.class);
	@Autowired
	private SprintDAO sprintDAO;
	@Override
	public List<Sprint> listarSprints(Proyecto p) 
	{
		return sprintDAO.listarSprints(p.getId());
	}

	@Override
	@Transactional
	public void agregar(Sprint sprint,Proyecto p) 
	{
		sprint.setProyecto(p);
		sprintDAO.agregar(sprint);
	}

	@Override
	public Sprint getSprintById(Integer sprintId) 
	{
		return sprintDAO.getById(sprintId);
	}

	@Override
	public void grabar(Sprint sprint) 
	{
		sprintDAO.grabar(sprint);
	}

	@Override
	public void borrarSprint(Sprint sprint) throws SprintAsociadaException 
	{
		// Hay que chequear que este sprint no tenga items de product backlog asociados
		// antes de borrar.
		if(sprint.getItems().size()>0)
			throw new SprintAsociadaException();
		else
			sprintDAO.borrar(sprint);
	}
	@Override
	public void asignarProductBacklogItems(Sprint sprint,List<ProductBacklogItem> listaItems)
	{
		// Viene una lista de items, y tengo que sincronizarla con lo que tiene
		// este sprint.
		log.trace("Esta vacia la listaItems?");
		if(listaItems==null || listaItems.isEmpty())
		{
			log.trace("Si, esta vacia, limpio el contenido");
			// hay que vaciar la lista de items del sprint.
			sprint.getItems().clear();
		}
		else
		{
			log.trace("No, no esta vacia, veo que tenga todo lo que necesito");
			// Si el item esta en listaItems pero no en la lista de items ya asignados en,
			// el sprint, hay que agregarlo.
			Iterator<ProductBacklogItem> iterator=listaItems.iterator();
			List<ItemSprint> itemsActuales=sprint.getItems();
			while(iterator.hasNext())
			{
				ProductBacklogItem item=iterator.next();
				log.trace("En listaItems vino el item de id "+item.getId()+", esta contenido en itemsActuales?");
				ItemSprint i=new ItemSprint();
				i.setSprint(sprint);
				i.setItem(item);
				if(!itemsActuales.contains(item))
				{
					log.trace("No esta incluido, hay que grabarlo. Sprint id:"+i.getSprint().getId()+", item id:"+i.getItem().getId());
					itemsActuales.add(i);
				}
			}
			// Ahora, si el item esta asignado al sprint pero no viene en la lista
			// de items, hay que sacarlo.
			log.trace("Ahora voy al reves y busco si a itemsActuales le sobran items");
			Iterator<ItemSprint> iteratorItemsActuales=itemsActuales.iterator();
			while(iteratorItemsActuales.hasNext())
			{
				ItemSprint i=iteratorItemsActuales.next();
				log.trace("En el sprint ya existe el item de id "+i.getItem().getId()+", si no esta en la lista, hay que sacarlo");
				if(!listaItems.contains(i.getItem()))
				{
					log.trace("No, no esta contenido, hay que borrarlo");
					iteratorItemsActuales.remove();
				}
			}
			sprint.setItems(itemsActuales);
		}
		sprintDAO.grabar(sprint);
	}
}
