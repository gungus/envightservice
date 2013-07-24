package com.envight.test.database.entitymanager;

import javax.persistence.EntityManager;

public interface EntityManagerFactory {

	public EntityManager getDefaultEntityManager() throws Exception;
}
