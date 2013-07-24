package com.envight.test.database;

import java.io.IOException;
import java.util.Properties;

public class DatasourceConfiguration {
	public String DEFAULT_PROPERTIES_FILE = "datasource.properties";
	private String propertiesFile = DEFAULT_PROPERTIES_FILE;
	
	private static Properties properties;

	public DatasourceConfiguration() {}

	public DatasourceConfiguration( String propertiesFile ) {
		this.propertiesFile = propertiesFile;
	}
	
	private Properties getProperties() throws IOException {
		if( properties == null ) {
			Properties p = new Properties();
			p.load( Thread.currentThread().getContextClassLoader().getSystemResourceAsStream( propertiesFile ) );
			properties = p;
		}
		return properties;
	}

	public String getJndiName() throws IOException {
		return getProperties().getProperty( "jdbc.jndi.name" );
	}
	
	public String getConnectionUrl() throws IOException {
		return getProperties().getProperty( "jdbc.connection.url" );
	}
	
	public String getJdbcDriverClass() throws IOException {
		return getProperties().getProperty( "jdbc.driver.class" );
	}

	public String getUser() throws IOException {
		return getProperties().getProperty( "jdbc.user" );
	}
	
	public String getPassword() throws IOException {
		return getProperties().getProperty( "jdbc.password" );
	}	
	
	public String getPersistenceUnit() throws IOException {
			return getProperties().getProperty( "javax.persistence.unit" );
		}	
}
