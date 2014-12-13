package emem.node.model;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import emem.node.util.Constant;

public class NodeInfoModel extends Model {

	public NodeInfoModel() {
		super();
		this.db = mongo.getDB(Constant.DBNAME_TOP);
	}

	public void init(String name) {
		collection = db.getCollection(name);
	}

	public void doAction(Map<String, Object> map) {
		map.put("date", new Date());
		DBObject object = new BasicDBObject(map);
		collection.insert(object);
	}
}
