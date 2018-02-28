package com.fran.persistence;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fran.config.DBConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAOFactory extends DaoFactory {
	
	private static DAO dao_instance = null;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MongoDAOFactory.class);
	
	/**
	 * This method creates a mongoDB client.
	 * This method is similar to "createConnection".
	 * 
	 * @return MongoDB client.
	 */
	public static MongoClient createMongoClient() {
		LOGGER.info("Requesting a new connection to the DB...");

		// MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoClient mongoClient = new MongoClient(DBConfig.getInstance().getDbUrl(), Integer.valueOf(DBConfig.getInstance().getDbPort()));

		// Conexión con credenciales
		/*MongoCredential credential = MongoCredential.createCredential(user, database, password);
		MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
		MongoClient mongoClient = new MongoClient(new ServerAddress("host1", 27017), Arrays.asList(credential), options);*/

		return mongoClient;
	}
	
	public static MongoDatabase getMongoDataBase(MongoClient client, String databaseName) {
		LOGGER.info("Switching to database {}...", databaseName);
		MongoDatabase db = client.getDatabase(databaseName);
		return db;
	}
	
	public static MongoCollection<Document> getMongoColletion(MongoDatabase database, String collectionName) {
		LOGGER.info("Getting collection {}...", collectionName);
		MongoCollection<Document> mongoCollection = database.getCollection(collectionName);
		return mongoCollection;
	}

	@Override
	public DAO getMongoDAO() {
		if (dao_instance == null) {
			dao_instance = MongoDAO.getInstance();
		}
		return dao_instance;
	}
}
