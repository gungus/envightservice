package com.envight.webservice;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.envight.jndi.RouteServiceLocator;
import com.envight.service.RouteService;
import com.envight.service.model.Location;
import com.envight.webservice.EnvightService;
import com.envight.webservice.model.Session;
import com.envight.webservice.model.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EnvightServiceTest {
	@Mock private RouteServiceLocator routeService; 
	@InjectMocks private EnvightService envightService = new EnvightService(); 
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks( this );
	}
	
	@Test
    public void successfulStep() {
		String sessionId = "session";
		Date now = new Date(); 
		Location SUNDSVALL = new Location(); 
		SUNDSVALL.setLatitude( 62.3833333 );
		SUNDSVALL.setLongitude( 17.3 );
		
		when( routeService.step( sessionId, now, SUNDSVALL ) ).thenReturn( sessionId );
		
		Step step = new Step(); 
		step.setSessionId( sessionId );
		step.setWhen( now );
		step.setLatitude( SUNDSVALL.getLatitude() );
		step.setLongitude( SUNDSVALL.getLongitude() );
		Session session = envightService.step( step );
		
		assertTrue( now.before( session.getResponseTime() ) );
		assertEquals( sessionId, session.getSessionId() );
		assertEquals( Session.STATUS_OK, session.getStatus() );
		assertNull( session.getMessage() );
    }
}
