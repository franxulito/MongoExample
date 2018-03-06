package com.fran.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fran.exception.DAOException;

public abstract class DaoFactory {
	
	// Logger
	private final static Logger LOGGER = LoggerFactory.getLogger(DaoFactory.class);
	
	public static DaoFactory createFactory(String className) throws DAOException {
		LOGGER.info("Creating the specific factory for {} ...", className);
		Class<?> specificFactory = null;
		try {
			specificFactory = Class.forName(className);
			
		} catch (ClassNotFoundException e) {
			throw new DAOException("Error getting the instance for " + className, e);
		}
		
		LOGGER.info("Instance is getting well.");
		LOGGER.info("Changing type for instance...");
		DaoFactory factory = null;
		try {
			factory = (DaoFactory)specificFactory.newInstance();
		} catch (InstantiationException e) {
			throw new DAOException("Error during the conversion", e);
		} catch (IllegalAccessException e) {
			throw new DAOException("Error during the conversion", e);
		}
		LOGGER.info("Instance for {} is getted well", className);
		/*
		// Si no quisierais hacer el instanciado por reflexión, tendríais que definir un switch con
		// una serie de opciones. Ejemplo:
		int valorQueRecibo = 1;
		switch (valorQueRecibo) {
			case 1:
					// Por ejemplo instancio la factoría de mongo
					factory = new MongoDAOFactory();
				break;
			case 2:
				// Instancio otro tipo de factoria. Por supuesto, en este ejemplo, esa implementación
				// no existe. Pero así creo que queda más claro.
				factory = new OracleDAOFactory();
			break;
	
			default:
				break;
		}*/
		
		return factory;
	}
	
	/**
	 * @return The mongodb DAO.
	 */
	public abstract DAO getMongoDAO();

}
