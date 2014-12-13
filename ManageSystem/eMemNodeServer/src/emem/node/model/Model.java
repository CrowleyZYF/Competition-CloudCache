package emem.node.model;

import java.net.UnknownHostException;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import emem.node.util.Constant;

public abstract class Model {

	MongoClient mongo;
	DB db;
	DBCollection collection;

	public Model() {
		try {
			mongo = new MongoClient(Constant.DBHOST);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract void init(String name);

	public abstract void doAction(Map<String, Object> map);

	public void close() {
		mongo.close();
	}
}
