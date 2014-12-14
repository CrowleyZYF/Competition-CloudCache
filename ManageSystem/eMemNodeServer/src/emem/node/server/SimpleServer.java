package emem.node.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import emem.node.util.Constant;

public class SimpleServer implements Runnable {

	public void run() {
		try {
			ServerSocket s = new ServerSocket(Constant.PORT);

			while (true) {
				Socket incoming = s.accept();
				Runnable r = new SimpleServerHandle(incoming);
				Thread t = new Thread(r);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
