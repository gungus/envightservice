package com.envight.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class JBossLocalServiceLocator {
	private String localJndiName;
	private Class clazz; 
	
	protected String getLocalJndiName() {
		return localJndiName;
	}
		
	public JBossLocalServiceLocator( String localJndiName, Class clazz ) {
		this.localJndiName = localJndiName; 
		this.clazz = clazz; 
	}

	public JBossLocalServiceLocator( String localJndiName, String className ) throws ClassNotFoundException {
		this.localJndiName = localJndiName; 
		this.clazz = Class.forName( className );
	}
	
	public Object getService() throws NamingException {
		InitialContext ctx = new InitialContext();
		return PortableRemoteObject.narrow( ctx.lookup( localJndiName ), clazz );
	}

}
