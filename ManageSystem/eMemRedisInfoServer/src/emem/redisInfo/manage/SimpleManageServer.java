package emem.redisInfo.manage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import emem.redisInfo.util.Constant;

public class SimpleManageServer implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket s = new ServerSocket(Constant.PORT);

			while (true) {
				Socket incoming = s.accept();
				Runnable r = new SimpleManageServerHandle(incoming);
				Thread t = new Thread(r);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
