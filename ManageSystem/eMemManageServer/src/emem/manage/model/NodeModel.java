package emem.manage.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import emem.manage.util.Constant;

public class NodeModel {

	private DB systemDB;
	private DB infoDB;
	private DBCollection collection;
	private DBCollection infoCollection;
	private String ip = "default";

	public NodeModel() {
		systemDB = Model.getMongo().getDB(Constant.DBNAME_SYSTEM);
		infoDB = Model.getMongo().getDB(Constant.DBNAME_NODE_INFO);
		collection = systemDB.getCollection(Constant.NODE_COLLECTION);
	}

	public NodeModel(String name) {
		this();
		infoCollection = infoDB.getCollection(name);
		this.ip = name;
	}

	public void infoInsert(Map<String, Object> map) {
		map.put("date", new Date());
		DBObject object = new BasicDBObject(map);
		infoCollection.insert(object);
	}

	public void update(Map<String, Object> map) {
		map.put("ip", ip);
		Map<String, Map<String, Object>> data = new HashMap<String, Map<String, Object>>();
		data.put("$set", map);
		collection.update(new BasicDBObject("ip", ip), new BasicDBObject(data),
				false, false);
	}
}
