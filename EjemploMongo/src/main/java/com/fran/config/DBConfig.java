package com.fran.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConfig {
	// Log
	private static final Logger LOGGER = LoggerFactory.getLogger(DBConfig.class);

	// Default properties de BBDD
	private final String DB_URL = "localhost";
	private final String DB_PORT = "27017";
	
	private final String DB_NAME = "myDatabase";
	private final String COLLECTION_NAME = "myCollection";

	private final String DEFAULT_DB_USER = "x";
	private final String DEFAULT_DB_PASSWORD = "x";
	
	// Singleton
	private static DBConfig instance;

	/**
	 * Return a singleton instance
	 */
	public static synchronized DBConfig getInstance() {
		LOGGER.info("Getting instance for configuration");
		if (instance == null) {
			instance = new DBConfig();
		}
		return instance;
	}

	public String getDbUrl() {
		return DB_URL;
	}

	public String getDbPort() {
		return DB_PORT;
	}


	public String getDbUser() {
		return DEFAULT_DB_USER;
	}

	public String getDbPass() {
		return DEFAULT_DB_PASSWORD;
	}
	
	public String getDbName() {
		return DB_NAME;
	}
	
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
