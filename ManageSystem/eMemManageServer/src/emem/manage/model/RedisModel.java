package emem.manage.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import emem.manage.util.Constant;

public class RedisModel {

	private DB systemDB;
	private DB infoDB;
	private DBCollection collection;
	private DBCollection infoCollection;
	private String ip_port = "default";

	public RedisModel() {
		systemDB = Model.getMongo().getDB(Constant.DBNAME_SYSTEM);
		infoDB = Model.getMongo().getDB(Constant.DBNAME_REDIS_INFO);
		collection = systemDB.getCollection(Constant.REDIS_COLLECTION);
	}

	public RedisModel(String name) {
		this();
		infoCollection = infoDB.getCollection(name);
		this.ip_port = name;
	}

	public void initInfoCollection(String name) {
		infoCollection = infoDB.getCollection(name);
		this.ip_port = name;
	}

	public void infoInsert(Map<String, Object> map) {
		map.put("date", new Date());
		DBObject object = new BasicDBObject(map);
		infoCollection.insert(object);
	}

	public void update(Map<String, Object> map) {
		map.put("ip:port", ip_port);
		Map<String, Map<String, Object>> data = new HashMap<String, Map<String, Object>>();
		data.put("$set", map);
		collection.update(new BasicDBObject("ip:port", ip_port),
				new BasicDBObject(data), false, false);
	}

}
