package emem.manage.server;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

import emem.manage.util.Constant;

public class RedisServer extends Server {

	private int threadNum = Constant.REDIS_THREAD_NUMBER;

	public RedisServer(int port) {
		super(port);
		this.excutor = Executors.newFixedThreadPool(threadNum);
	}

	protected void newThread(SocketChannel channel) {
		excutor.submit(new RedisServerHandle(channel));
	}
}
