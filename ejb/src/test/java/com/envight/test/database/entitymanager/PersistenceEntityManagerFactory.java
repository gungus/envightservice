package com.envight.test.database.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceEntityManagerFactory implements EntityManagerFactory {
	private String persistenceUnit;

	public PersistenceEntityManagerFactory( String persistenceUnit ) {
		this.persistenceUnit = persistenceUnit;
	}

	public EntityManager getDefaultEntityManager() throws Exception {
		return Persistence.createEntityManagerFactory( persistenceUnit ).createEntityManager();	
	}
}
