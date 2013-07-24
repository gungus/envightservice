package com.envight.service;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.envight.service.model.Location;

/**
 * Handle steps on a route. 
 * 
 * @author ggustavs
 *
 */
@Stateless
public class RouteServiceEJB implements RouteService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Record next step on a route. 
	 * 
	 * NOTE: Location should not have been persisted already. 
	 */
	public String step( String sessionId, Date when, Location where ) {
//		Route route = new Route();
//		route.setSentAt( when );
//		route.setLocation( where );
//		
//		// @todo Unnecessary to load session, should be enough to have the sessionId.
//		route.setSession( entityManager.find( Session.class, sessionId ) ); 
//		entityManager.persist( route );
		entityManager.persist( where );
		return sessionId; 
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
