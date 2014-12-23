package emem.manage.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
	protected ServerSocketChannel serversocketchannel;
	protected Selector selector;
	protected int port;
	protected ExecutorService excutor;

	public Server(int port) {
		this.port = port;
		initChannel();
		initSelector();
	}

	public void run() {
		System.out.println("Server Start!");
		try {
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void listen() throws IOException {
		System.out.println("Start Listen!");
		while (true) {
			int num = selector.select();
			if (num > 0) {
				Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

				while (ite.hasNext()) {
					SelectionKey key = ite.next();

					ite.remove();

					if (key.isAcceptable()) {
						SocketChannel channel = ((ServerSocketChannel) key
								.channel()).accept();
						channel.configureBlocking(false);
						channel.register(selector, SelectionKey.OP_READ);
						System.out.println("客户端请求连接事件！");

					} else if (key.isReadable()) {
						System.out.println("读取客户端发送消息");
						SocketChannel channel = (SocketChannel) key.channel();
						newThread(channel);
						key.cancel();
					}
				}
			} else {
				serversocketchannel.register(selector, SelectionKey.OP_ACCEPT);
			}
		}
	}

	protected void newThread(SocketChannel channel) {
		
	}

	private void initChannel() {
		try {
			serversocketchannel = ServerSocketChannel.open();
			serversocketchannel = ServerSocketChannel.open();
			serversocketchannel.bind(new InetSocketAddress(port));
			serversocketchannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initSelector() {
		try {
			selector = Selector.open();
			serversocketchannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
