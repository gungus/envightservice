package com.envight.test.database;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.IColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;

import com.envight.test.database.datasource.JndiDatasourceFactory;
import com.envight.test.database.entitymanager.PersistenceEntityManagerFactory;

public abstract class DbUnit {
	private String dataset = "testdata.xml";

	private EntityManager entityManager;
	private DataSource datasource; 

	private IDatabaseTester databaseTester;

	@Before
	public void before() throws Exception {
		System.out.println( "initializing database data..." );
		DatasourceConfiguration dcf = new DatasourceConfiguration();
		
		setEntityManager( new PersistenceEntityManagerFactory( dcf.getPersistenceUnit() ).getDefaultEntityManager() );
		setDatasource( new JndiDatasourceFactory( dcf.getJndiName() ).getDefaultDatasource() );
		
		if( databaseTester == null ) {
			databaseTester = getDatabaseTester();
			databaseTester.setDataSet( getDataSet() );
			databaseTester.onSetup();
		}
		System.out.println( "initializing database data done." );
	}
		
	protected IDatabaseTester getDatabaseTester() {
		return new DataSourceDatabaseTester( datasource ) {
			@Override
			public IDatabaseConnection getConnection() throws Exception {
				IDatabaseConnection databaseConnection = super.getConnection();
				DatabaseConfig databaseConfig = databaseConnection.getConfig();
//				databaseConfig.setProperty( DatabaseConfig.PROPERTY_PRIMARY_KEY_FILTER, new PrimaryKeyFilter() );
				return databaseConnection;
			}
		};		
	}

	protected IDataSet getDataSet() throws DataSetException, IOException {
		return new FlatXmlDataSet( Thread.currentThread().getContextClassLoader().getResource( dataset ) );
	}

	protected class PrimaryKeyFilter implements IColumnFilter {
		public boolean accept(String tableName, Column column) {
			return true;
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
}
