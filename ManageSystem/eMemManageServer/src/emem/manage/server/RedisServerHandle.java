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
		try {
			this.model = new RedisModel(getHostPort(channel.getRemoteAddress()
					.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.model = new RedisModel();
			e.printStackTrace();
		}
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
		// for (Map.Entry<String, Object> entry : result.entrySet()) {
		// System.out.println(entry.getKey() + "---->" + entry.getValue());
		// }
		model.infoInsert(result);
		model.update(result);
	}

	private String getHostPort(String name) {
		return name.replace("/", "");
	}

}
