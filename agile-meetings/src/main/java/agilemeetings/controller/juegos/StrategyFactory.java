package agilemeetings.controller.juegos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.aop.framework.Advised;

import agilemeetings.service.impl.JuegoServiceImpl;

@Repository
public class StrategyFactory 
{
	@Autowired
	private ApplicationContext applicationContext;
	private static Logger LOG=LogManager.getLogger(JuegoServiceImpl.class);
	private Map<Class<?>,List<Object>> annotatedTypes=new HashMap<Class<?>,List<Object>>();
	private Map<Class<? extends Object>,Strategy> strategyCache=new HashMap<Class<? extends Object>,Strategy>();
	
	@PostConstruct
	public void init() 
	{

		Map<String, Object> annotatedBeanClasses = applicationContext.getBeansWithAnnotation(Strategy.class);
		sanityCheck(annotatedBeanClasses.values());
		for (Object bean : annotatedBeanClasses.values()) 
		{
			Strategy strategyAnnotation = strategyCache.get(bean.getClass());
			getBeansWithSameType(strategyAnnotation).add(bean);
		}

	}

	private void sanityCheck(Collection<Object> annotatedBeanClasses) 
	{

		Set<String> usedStrategies = new HashSet<>();
		for (Object bean : annotatedBeanClasses) 
		{

			Strategy strategyAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), Strategy.class);
			if (strategyAnnotation == null)
			{
				try 
				{
					Object target = ((Advised) bean).getTargetSource().getTarget();
					strategyAnnotation = AnnotationUtils.findAnnotation(target.getClass(), Strategy.class);
				} 
				catch (Exception e) 
				{
					LOG.error("Could not resolve Strategy annotation for spring bean!", e);
				}
			}
			strategyCache.put(bean.getClass(), strategyAnnotation);

			if (isDefault(strategyAnnotation)) 
			{
				ifNotExistAdd(strategyAnnotation.type(), "default", usedStrategies);
			}

			for (JuegoEnum profile : strategyAnnotation.juegos()) {
				ifNotExistAdd(strategyAnnotation.type(), profile, usedStrategies);
			}

		}
	}

	private void ifNotExistAdd(Class<? extends JuegoHelper> type, JuegoEnum profile, Set<String> usedStrategies) 
	{
		ifNotExistAdd(type, profile.name(), usedStrategies);
	}

	private void ifNotExistAdd(Class<? extends JuegoHelper> type, String profile, Set<String> usedStrategies) {
		if (usedStrategies.contains(createKey(type, profile))) {
			throw new RuntimeException("There can only be a single strategy for each type, found multiple for type '"+type+"' and profile '"+profile+"'");
		}
		usedStrategies.add(createKey(type, profile));
	}

	private String createKey(Class<?> type, String profile) 
	{
		return (type+"_"+profile).toLowerCase();
	}

	private List<Object> getBeansWithSameType(Strategy strategyAnnotation) 
	{
		List<Object> beansWithSameType = annotatedTypes.get(strategyAnnotation.type());
		if (beansWithSameType != null) 
		{
			return beansWithSameType;
		}
		else 
		{
			List<Object> newBeansList = new ArrayList<>();
			annotatedTypes.put(strategyAnnotation.type(), newBeansList);
			return newBeansList;
		}
	}

	private boolean isDefault(Strategy strategyAnnotation) 
	{
		return (strategyAnnotation.juegos().length == 0);
	}

	@SuppressWarnings("unchecked")
	public <T> T getStrategy(Class<T> strategyType, JuegoEnum juego) 
	{
		List<Object> strategyBeans = annotatedTypes.get(strategyType);
		Assert.notEmpty(strategyBeans, "No strategies found of type '"+ strategyType.getName()+"', are the strategies marked with @Strategy?");
		Object profileStrategy = findStrategyMatchingJuego(strategyBeans, juego);
		if (profileStrategy == null) 
		{
			throw new RuntimeException("No strategy found for type '"+ strategyType +"'");
		}
		return (T)profileStrategy;
	}
	private Object findStrategyMatchingJuego(List<Object> strategyBeans, JuegoEnum juego) 
	{
		Object defaultStrategy = null;
		for (Object bean : strategyBeans) 
		{
			Strategy strategyAnnotation = strategyCache.get(bean.getClass());
			if(juego != null) 
			{
				//Only iterate the profiles if a profile has been selected
				for (JuegoEnum j: strategyAnnotation.juegos()) 
				{
					if (j == juego) 
					{
						return bean;
					}
				}
			}
		}
		return defaultStrategy;
	}
}
