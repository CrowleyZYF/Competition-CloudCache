package emem.node.model;

import java.util.Map;

import org.bson.NewBSONDecoder;

import com.mongodb.BasicDBObject;

import emem.node.util.Constant;

public class NodeModel extends Model {

	String ip;

	public NodeModel() {
		super();
		db = mongo.getDB(Constant.DBNAME_SYSTEM);
		collection = db.getCollection(Constant.COLLECTION_NODE);
	}

	public void init(String name) {
		this.ip = name;
	}

	public void doAction(Map<String, Object> map) {
		try {
			map.put("ip", ip);
			collection.update(new BasicDBObject("ip", ip), new BasicDBObject(
					map), false, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
