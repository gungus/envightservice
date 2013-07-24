package com.envight.webservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.envight.jndi.RouteServiceLocator;
import com.envight.service.RouteService;
import com.envight.service.model.Location;
import com.envight.webservice.model.Credentials;
import com.envight.webservice.model.Session;
import com.envight.webservice.model.Step;

/**
 * RESTful JSON based web service to implement the mobile appliucation API.
 *  
 * @author ggustavs
 */
@Controller
public class EnvightService 
{
	@Autowired
	@Qualifier( value = "routeService" )
	RouteServiceLocator routeService;

	@Autowired
	@Qualifier( value = "sessionService" )
	SessionService sessionService;
	
	/**
	 * Begin session. 
	 * 
	 * Begin a ssession if credentials are valid. 
	 * 
	 * @param credentials authentication credentials. 
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST ) 
	public @ResponseBody Session begin( Credentials credentials ) {
		Session s = new Session(); 
		try {
			s.setSessionId( sessionService.beginSession( credentials ) );
			s.setResponseTime( new Date() );
		}
		catch( Throwable t ) {
			s.setStatus( Session.STATUS_FAIL );
			s.setMessage( t.getMessage() );
		}
		return s; 
	}
	
	/**
	 * Record a step. 
	 * 
	 * Stores the next step of a begun session. 
	 * 
	 * @param step the time and geographic location of the nest step.
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST ) 
	public @ResponseBody Session step( Step step ) {
		Session s = new Session(); 
		try {
			Location where = new Location();
			where.setLatitude( step.getLatitude() );
			where.setLongitude( step.getLongitude() );
			s.setSessionId( routeService.step( step.getSessionId(), step.getWhen(), where ) );
			s.setResponseTime( new Date() );
		}
		catch( Throwable t ) {
			s.setStatus( Session.STATUS_FAIL );
			s.setMessage( t.getMessage() );			
		}
		return s;
	}

	/**
	 * End session.
	 * 
	 * Session is ended no more incoming steps for this session are allowed. 
	 * 
	 * @param sessionId Session to end. 
	 * @return 
	 */
	@RequestMapping( method = RequestMethod.POST ) 
	public @ResponseBody Session end( String sessionId ) {
		Session s = new Session();
		try {
			s.setSessionId( sessionService.endSession( sessionId ) );
			s.setResponseTime( new Date() );
		}
		catch( Throwable t ) {
			s.setStatus( Session.STATUS_FAIL );
			s.setMessage( t.getMessage() );						
		}
		return s;
	}
}
