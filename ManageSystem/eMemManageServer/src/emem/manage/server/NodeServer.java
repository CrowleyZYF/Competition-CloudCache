package emem.manage.server;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import emem.manage.model.NodeModel;
import emem.manage.util.Constant;

public class NodeServer extends Server {

	private int threadNum = Constant.NODE_THREAD_NUMBER;
	private Map<String, Map<String, Object>> alive = new HashMap<String, Map<String, Object>>();

	public NodeServer(int port) {
		super(port);
		this.excutor = Executors.newFixedThreadPool(threadNum);
		this.alive = (new NodeModel()).initAlive();
	}

	protected void newThread(SocketChannel channel) {
		this.excutor.submit(new NodeServerHandle(channel, alive));
	}

	public void run() {
		// 首先起一个线程来监控alive.
		Thread t = new Thread(new NodeLiveHandle(alive));
		t.start();
		super.run();
	}

}
