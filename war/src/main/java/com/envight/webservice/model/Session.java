package com.envight.webservice.model;

import java.util.Date;

public class Session {
	public static final String STATUS_OK = "OK";
	public static final String STATUS_FAIL = "FAIL";
	
	private String sessionId;
	private Date responseTime = new Date(); 
	private String status = "OK"; 
	private String message; 

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
