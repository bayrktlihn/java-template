package io.bayrktlihn.template.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConfiguration {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

	static {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("TestPersistence");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return ENTITY_MANAGER_FACTORY;
	}

}
