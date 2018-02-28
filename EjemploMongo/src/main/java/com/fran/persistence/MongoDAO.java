package com.fran.persistence;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fran.config.DBConfig;
import com.fran.exception.DAOException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO implements DAO {

	private final Logger LOGGER = LoggerFactory.getLogger(MongoDAO.class);

	private static MongoDAO instance;

	public static synchronized MongoDAO getInstance() {
		if (instance == null) {
			instance = new MongoDAO();
		}
		return instance;
	}

	private MongoDAO() {
		super();
	}

	@Override
	public Document selectFirstElement(String collectionName) throws DAOException {
		LOGGER.info("Obteniendo el primer elemento de la colección {}", collectionName);
		Document myDoc = null;
		try (MongoClient client = MongoDAOFactory.createMongoClient()) {
			MongoDatabase db = MongoDAOFactory.getMongoDataBase(client, DBConfig.getInstance().getDbName());
			MongoCollection<Document> collection = MongoDAOFactory.getMongoColletion(db, collectionName);
			
			myDoc = collection.find().first();
		} catch (Exception e) {
			throw new DAOException("Error obteniendo el primer elemento", e);
		}
		return myDoc;
	}
	
	@Override
	public List<Document> select(String collectionName, Bson where) throws DAOException {	
		LOGGER.info("Obteniendo todos los documentos de la colección {} que coincidan con la clausula {}", collectionName, where.toString());
		
		List<Document> list = new ArrayList<>();
		FindIterable<Document> myDoc = null;
		try (MongoClient client = MongoDAOFactory.createMongoClient()){
			MongoDatabase db = MongoDAOFactory.getMongoDataBase(client, DBConfig.getInstance().getDbName());
			MongoCollection<Document> collection = MongoDAOFactory.getMongoColletion(db, collectionName);
			
			myDoc = collection.find(where);
			for (Document element : myDoc) {
				list.add(element);
			}
		} catch (Exception e) {
			throw new DAOException("Error obteniendo el primer elemento", e);
		}
		return list;
	}

	@Override
	public void insertOne(String collectionName) throws DAOException {
		LOGGER.info("Insertando un elemento en la colección {}", collectionName);
		Document document = new Document("name", "fran").append("age", 27);
		
		try (MongoClient client = MongoDAOFactory.createMongoClient()){
			MongoDatabase db = MongoDAOFactory.getMongoDataBase(client, DBConfig.getInstance().getDbName());
			MongoCollection<Document> collection = MongoDAOFactory.getMongoColletion(db, collectionName);
			
			collection.insertOne(document);
		} catch (Exception e) {
			throw new DAOException("Error obteniendo el primer elemento", e);
		}
		
	}

	@Override
	public void insertMany(String collectionName) throws DAOException {
		List<Document> list = new ArrayList<>();
		Document document = new Document("name", "alberto").append("age", 27);
		list.add(document);
		document = new Document("name", "juan").append("age", 24);
		list.add(document);
		document = new Document("name", "pepe").append("age", 25);
		list.add(document);
		
		LOGGER.info("Insertando varios elementos en la colección {}", collectionName);
		try (MongoClient client = MongoDAOFactory.createMongoClient()){
			MongoDatabase db = MongoDAOFactory.getMongoDataBase(client, DBConfig.getInstance().getDbName());
			MongoCollection<Document> collection = MongoDAOFactory.getMongoColletion(db, collectionName);
			
			collection.insertMany(list);
		} catch (Exception e) {
			throw new DAOException("Error obteniendo el primer elemento", e);
		}
	}
	
	@Override
	public void update(String collectionName, Bson where) throws DAOException {		
		LOGGER.info("Actualizando varios elementos en la colección {}", collectionName);
		try (MongoClient client = MongoDAOFactory.createMongoClient()){
			MongoDatabase db = MongoDAOFactory.getMongoDataBase(client, DBConfig.getInstance().getDbName());
			MongoCollection<Document> collection = MongoDAOFactory.getMongoColletion(db, collectionName);
			
			collection.updateMany(where, new Document("$set", new Document("name", "prueba")));
		} catch (Exception e) {
			throw new DAOException("Error obteniendo el primer elemento", e);
		}
	}

	@Override
	public void delete(MongoCollection<Document> collection) throws DAOException {
		// TODO Auto-generated method stub
		
	}



}
