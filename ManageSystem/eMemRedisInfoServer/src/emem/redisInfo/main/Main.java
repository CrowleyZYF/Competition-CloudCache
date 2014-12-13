package emem.redisInfo.main;

import emem.redisInfo.manage.SimpleManageServer;

public class Main {
	public static void main(String[] args) {
		SimpleManageServer server = new SimpleManageServer();
		server.run();
	}
}
