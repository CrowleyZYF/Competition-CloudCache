package emem.manage.main;

import emem.manage.model.Model;
import emem.manage.server.NodeServer;
import emem.manage.server.RedisServer;
import emem.manage.util.Constant;

public class Main {
	public static void main(String[] args) {
		(new Model()).setMongo(Constant.DBHOST, Constant.DBPORT);
		Thread nodeListener = new Thread(new NodeServer(Constant.NODE_PORT));
		nodeListener.start();
		Thread redisListener = new Thread(new RedisServer(Constant.REDIS_PORT));
		redisListener.start();
	}
}
