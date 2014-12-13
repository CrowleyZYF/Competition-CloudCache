package emem.redisInfo.model;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import emem.redisInfo.util.Constant;

public class RedisInfoModel extends Model {

	public void init(String name) {
		db = mongo.getDB(Constant.DBNAME_INFO);
		collection = db.getCollection(name);
	}

	public void doAction(Map<String, Object> data) {
		BasicDBObject object = new BasicDBObject(data);
		collection.insert(object);
	}

}