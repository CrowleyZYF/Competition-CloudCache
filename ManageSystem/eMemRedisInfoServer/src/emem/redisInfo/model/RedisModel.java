package emem.redisInfo.model;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import emem.redisInfo.util.Constant;

public class RedisModel extends Model {

	private String ip_host;

	public void init(String name) {
		db = mongo.getDB(Constant.DBNAME_INSTANCE);
		collection = db.getCollection(Constant.COLLECTION_REDIS);
		this.ip_host = name;
	}

	public void doAction(Map<String, Object> map) {
		map.put("ip:host", ip_host);
		collection.update(new BasicDBObject("ip:host", ip_host),
				new BasicDBObject(map), false, false);
	}

	public Map findByIpPort() {
		BasicDBObject query = new BasicDBObject("ip:host", ip_host);
		DBObject result = collection.findOne(query);
		return result.toMap();
	}
}
