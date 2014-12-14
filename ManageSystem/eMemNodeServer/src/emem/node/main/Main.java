package emem.node.main;

import emem.node.server.SimpleServer;

public class Main {

	public static void main(String[] args) {
		SimpleServer server = new SimpleServer();
		server.run();
	}

}
