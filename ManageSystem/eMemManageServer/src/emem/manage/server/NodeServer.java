package emem.manage.server;

import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

import emem.manage.util.Constant;

public class NodeServer extends Server {

	private int threadNum = Constant.NODE_THREAD_NUMBER;

	public NodeServer(int port) {
		super(port);
		this.excutor = Executors.newFixedThreadPool(threadNum);
	}

	protected void newThread(SocketChannel channel) {
		// this.excutor.submit(new NodeServerHandle(channel));
		Thread t = new Thread(new NodeServerHandle(channel));
		t.start();
	}
}
