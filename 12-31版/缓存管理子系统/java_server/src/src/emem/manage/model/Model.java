package emem.manage.model;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class Model {
	/*
	 * 使用单例模式，因为整个系统的数据库
	 */

	private static MongoClient mongo;

	public void setMongo(MongoClient mongo) {
		Model.mongo = mongo;
	}

	public void setMongo(String host) {
		try {
			Model.mongo = new MongoClient(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("new mongo error!");
			e.printStackTrace();
		}
	}

	public void setMongo(String host, int port) {
		try {
			Model.mongo = new MongoClient(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("new mongo error!");
			e.printStackTrace();
		}
	}

	public static MongoClient getMongo() {
		return mongo;
	}
}
