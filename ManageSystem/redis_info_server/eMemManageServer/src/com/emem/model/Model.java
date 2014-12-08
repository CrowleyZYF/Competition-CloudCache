package com.emem.model;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;

import com.emem.util.Constant;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Model {
	MongoClient mongoClient;
	DB db;

	public Model() {
		try {
			this.mongoClient = new MongoClient(Constant.DBHOST, Constant.DBPORT);
			this.db = mongoClient.getDB(Constant.DBNAME);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void insert(Map<String, Object> data) {
		BasicDBObject object = new BasicDBObject(data);
		DBCollection collection = db.getCollection(Constant.COLLECTION);
		collection.insert(object);
	}

	public void close() {
		mongoClient.close();
	}

}