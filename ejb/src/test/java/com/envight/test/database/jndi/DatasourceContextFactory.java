package com.envight.test.database.jndi;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.sql.DataSource;

import org.hsqldb.jdbc.jdbcDataSource;

import com.envight.test.database.DatasourceConfiguration;

import static org.mockito.Mockito.*;

public class DatasourceContextFactory implements InitialContextFactory {
	private DatasourceConfiguration dcf = new DatasourceConfiguration();
	
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		Context context = mock( Context.class );

		// Construct REAL DataSource
		try {
			Class.forName( dcf.getJdbcDriverClass() );

			jdbcDataSource ds = new jdbcDataSource();
			ds.setDatabase( dcf.getConnectionUrl() );
			ds.setUser( dcf.getUser() );
			ds.setPassword( dcf.getPassword() );
			
			when( context.lookup( dcf.getJndiName() ) ).thenReturn( ds );
		}
		catch( IOException e ) {
			e.printStackTrace();
			throw new NamingException( e.getMessage() );
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NamingException( e.getMessage() );
		}
		return context;
	}		
}
