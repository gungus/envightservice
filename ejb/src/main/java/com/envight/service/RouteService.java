package com.envight.service;

import java.util.Date;

import javax.ejb.Local;

import com.envight.service.model.Location;

/**
 * Handle a step. 
 * 
 * @author ggustavs
 */
public interface RouteService {
	/**
	 * 
	 * @param sessionId, unique session identifier 
	 * @param when, time of step
	 * @param where, location of step 
	 * @return status of operation
	 */
	public String step( String sessionId, Date when, Location where );
}
