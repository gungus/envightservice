package com.envight.service.model;

import static org.junit.Assert.assertNotNull;

import java.sql.DriverManager;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.connection.DriverManagerConnectionProvider;
import org.hsqldb.jdbc.jdbcDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.envight.test.database.DbUnit;

public class LocationTest extends DbUnit {
	private static Location SUNDSVALL;
	
	@Before
	public void setUp() {
		SUNDSVALL = new Location(); 
		SUNDSVALL.setLatitude( 62.3833333 );
		SUNDSVALL.setLongitude( 17.3 );
	}
	
	@Test 
	public void create() {
		Location location = new Location(); 
		location.setLatitude( SUNDSVALL.getLatitude() );
		location.setLongitude( SUNDSVALL.getLongitude() );
		
		getEntityManager().getTransaction().begin();
		getEntityManager().persist( location );
		getEntityManager().getTransaction().commit();

		location = null; 
		getEntityManager().getTransaction().begin();
		Query query = getEntityManager().createQuery( "select x from Location x where x.latitude = :lat and x.longitude = :long" );
		query.setParameter( "lat", SUNDSVALL.getLatitude() );
		query.setParameter( "long", SUNDSVALL.getLongitude() );
		location = (Location) query.getSingleResult();
		getEntityManager().getTransaction().commit();
		
		assertNotNull( location.getId() );
	}

}
