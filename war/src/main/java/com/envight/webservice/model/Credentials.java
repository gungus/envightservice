package com.envight.webservice.model;

public class Credentials {
	private String user;
	private String password; // clear text, protected by ssl. 

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
