package emem.node.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import emem.node.model.Model;
import emem.node.model.NodeModel;
import emem.node.model.NodeInfoModel;
import emem.node.util.Parser;

public class SimpleServerHandle implements Runnable {
	private Socket incoming;
	Parser parser = new Parser();
	Model m;

	public SimpleServerHandle(Socket s) {
		this.incoming = s;
	}

	public void run() {
		System.out.println("thread start");
		doParse();
		doMongo();
		doClear();
		System.out.println("thread end");
	}

	private void doParse() {
		try {
			Scanner scanner = new Scanner(incoming.getInputStream());
			parser.parse(scanner);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doMongo() {
		doHistory();
		doNode();
	}

	private void doHistory() {
		m = new NodeInfoModel();
		m.init(getNodeIp());
		m.doAction(parser.getResult());
		m.close();
	}

	private void doNode() {
		m = new NodeModel();
		m.init(getNodeIp());
		m.doAction(parser.getResult());
		m.close();
	}

	private String getNodeIp() {
		return incoming.getInetAddress().toString().replaceAll("/", "");
	}

	private void doClear() {
		try {
			incoming.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
