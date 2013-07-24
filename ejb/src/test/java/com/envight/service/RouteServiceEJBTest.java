package com.envight.service;


import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.persistence.NamedQuery;
import javax.persistence.Query;

import org.junit.Test;

import com.envight.service.model.Location;
import com.envight.test.database.DbUnit;

public class RouteServiceEJBTest extends DbUnit {
	RouteServiceEJB subject = new RouteServiceEJB();
	
	@Test 
	public void step() {
		Location SUNDSVALL = new Location(); 
		SUNDSVALL.setLatitude( 62.3833333 );
		SUNDSVALL.setLongitude( 17.3 );

		subject.setEntityManager( getEntityManager() );

		getEntityManager().getTransaction().begin();
		subject.step( "session", new Date(), SUNDSVALL );
		getEntityManager().getTransaction().commit();

		getEntityManager().getTransaction().begin();
		Query query = getEntityManager().createQuery( "select x from Location x where x.latitude = :lat and x.longitude = :long" );
		query.setParameter( "lat", SUNDSVALL.getLatitude() );
		query.setParameter( "long", SUNDSVALL.getLongitude() );
		Location location = (Location) query.getSingleResult();
		getEntityManager().getTransaction().commit();
		
		assertNotNull( location.getId() );
	}
}
