package emem.manage.main;

import emem.manage.model.Model;
import emem.manage.server.NodeServer;
import emem.manage.server.RedisServer;
import emem.manage.util.Constant;

public class Main {

	private static String dbHost = Constant.DBHOST;
	private static int dbPort = Constant.DBPORT;
	
	public static void main(String[] args) {
		init(args);
		(new Model()).setMongo(dbHost, dbPort);
		Thread nodeListener = new Thread(new NodeServer(Constant.NODE_PORT));
		nodeListener.start();
		Thread redisListener = new Thread(new RedisServer(Constant.REDIS_PORT));
		redisListener.start();
	}

	private static void init(String[] args) {
		int len = args.length;
		if (len > 2) {

		} else {
			switch (len) {
			case 0:
				break;
			case 1:
				dbHost = args[0];
				break;
			case 2:
				dbHost = args[0];
				dbPort = Integer.parseInt(args[1]);
				break;
			}
		}
	}

}
