package com.fran.persistence;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.fran.exception.DAOException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public interface DAO {

	public Document selectFirstElement(String collectionName) throws DAOException;
	
	public List<Document> select(String collectionName, Bson where) throws DAOException;
	
	public void insertOne(String collectionName) throws DAOException;
	
	public void insertMany(String collectionName) throws DAOException;
	
	public void update(String collectionName, Bson where) throws DAOException;
	
	public void delete(MongoCollection<Document> collection) throws DAOException;
	
		
}
