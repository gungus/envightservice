package com.envight.jndi;

import java.util.Date;

import javax.naming.NamingException;

import com.envight.service.RouteService;
import com.envight.service.model.Location;

public class RouteServiceLocator extends JBossLocalServiceLocator implements RouteService {

	public RouteServiceLocator() {
		super( "envightserviceear/RouteService/local", RouteService.class );
	}
	
	@Override
	public String step( String sessionId, Date when, Location where )  {
		RouteService service;
		try {
			service = (RouteService) getService();
		} catch (NamingException e) {
			e.printStackTrace();
			return null; 
		}
		return service.step( sessionId, when, where );
	}

}
