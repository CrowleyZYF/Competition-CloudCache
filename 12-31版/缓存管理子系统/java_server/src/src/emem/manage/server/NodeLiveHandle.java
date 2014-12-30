package emem.manage.server;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import emem.manage.model.NodeModel;

public class NodeLiveHandle implements Runnable {

	private String command = "../scripts/is_start_host.sh";
	private Map<String, Map<String, Object>> map;

	public NodeLiveHandle(Map<String, Map<String, Object>> map) {
		this.map = map;
	}

	public void run() {
		while (true) {
			Date threshold = new Date(new Date().getTime() - 10 * 60000);
			for (Entry<String, Map<String, Object>> e : map.entrySet()) {
				if ((int) e.getValue().get("state") == 1
						&& threshold.getTime() > ((Date) (e.getValue()
								.get("update_time"))).getTime()) {
					if (ping(e.getKey())) {
						e.getValue().put("update_time", new Date());
						e.getValue().put("state", 1);
					} else {
						e.getValue().put("update_time", new Date());
						e.getValue().put("state", 0);
						NodeModel model = new NodeModel(e.getKey());
						model.update(e.getValue());
					}
				}
			}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean ping(String host) {
		try {
			Process p = Runtime.getRuntime().exec(command + " " + host);
			@SuppressWarnings("resource")
			Scanner s = new Scanner(p.getInputStream());
			if (s.nextLine().equals("yes")) {
				return true;
			}
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
