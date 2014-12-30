package emem.manage.server;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Scanner;

import emem.manage.model.RedisModel;
import emem.manage.parser.Parser;
import emem.manage.parser.RedisParser;

public class RedisServerHandle implements Runnable {

	private SocketChannel channel;
	private Map<String, Object> result;
	private RedisModel model;

	public RedisServerHandle(SocketChannel channel) {
		this.channel = channel;
		this.model = new RedisModel();
	}

	public void run() {
		doParse();
		doMongo();
	}

	private void doParse() {
		Scanner scanner = new Scanner(channel);
		Parser p = new RedisParser();
		p.parse(scanner);
		result = p.getResult();
	}

	private void doMongo() {
		try {
			model.initInfoCollection(getHostPort(channel.getRemoteAddress()
					.toString()));
			model.infoInsert(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.update(result);
	}

	private String getHostPort(String name) {
		return name.replace("/", "").split(":")[0] + result.get("tcp_port");
	}

}
