package com.envight.test.database.datasource;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class JndiDatasourceFactory implements DatasourceFactory {
	private String jndiName; 
	
	public JndiDatasourceFactory( String jndiName ) {
		this.jndiName = jndiName; 
	}

	public DataSource getDefaultDatasource() throws Exception {
		return (DataSource) new InitialContext().lookup( jndiName );
	}

}
