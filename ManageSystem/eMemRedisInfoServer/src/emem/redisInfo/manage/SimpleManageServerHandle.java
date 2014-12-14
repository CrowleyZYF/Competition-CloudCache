package emem.redisInfo.manage;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import emem.redisInfo.model.Model;
import emem.redisInfo.model.RedisInfoModel;
import emem.redisInfo.model.RedisModel;
import emem.redisInfo.util.RedisInfoParser;

public class SimpleManageServerHandle implements Runnable {

	private Socket incoming;
	private Map<String, Object> datas = new HashMap<String, Object>();
	private Model m;

	public SimpleManageServerHandle(Socket s) {
		this.incoming = s;
	}

	public void run() {
		System.out.println("thread start");
		doParse();
		doMongo(); // 将redis的监控信息存入mongo中
		doClear();
		System.out.println("thread end");
	}

	private void doParse() {
		try {

			Scanner s = new Scanner(incoming.getInputStream());
			parseRawMessage(s);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseRawMessage(Scanner s) {
		RedisInfoParser parser = new RedisInfoParser();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] parts = parser.parse(line);
			if (parser.isContain(parts[0])) {
				datas.put(parser.getDbName(parts[0]), parts[1]);
			}
		}
	}

	private void doMongo() {
		datas.put("date", new Date());
		doInfoHistory();
		doRedis();
	}

	private void doInfoHistory() {
		m = new RedisInfoModel();
		m.init(getIpHost());
		m.doAction(datas);
		m.close();
	}

	private void doRedis() {
		m = new RedisModel();
		m.init(getIpHost());
		doElastic();
		m.doAction(datas);
		m.close();
	}

	private String getIpHost() {
		return (incoming.getInetAddress().toString()).replaceAll("/", "") + ":"
				+ datas.get("tcp_port");
	}

	/**
	 * 根据insert data 确定是否需要把redis分配的内存进行修改. 目前策略是依照ArrayList的伸缩方式. 上限是用户购买的内存大小.
	 * 下限通过系统的配置来确定.
	 */

	private void doElastic() {
		RedisModel model = new RedisModel();
		model.init(getIpHost());
		Map map = model.findByIpPort();
		// 通过周期的心跳信息判断是否需要动态调整redis的内存
		Double used = Double.parseDouble((String) datas.get("used_memory"));
		Double max = Double.parseDouble((String) map.get("max_memory"));
		Double min = Double.parseDouble((String) map.get("min_memory"));
		int threshold = Integer.parseInt((String) map.get("threshold"));
		int num = Integer.parseInt((String) map.get("number"));
		Double ratio = used / max;
		if (ratio > .5) {
			if (num > threshold) {
				doExtend(used, max);
				num = 0;
			} else {
				num++;
			}
		} else if (ratio < .25) {
			if (num > threshold) {
				doContract(used, min);
				num = 0;
			} else {
				num++;
			}
		} else {
			num = --num > 0 ? num : 0;
		}
		datas.put("number", num);
	}

	private void doExtend(double used, double max) {
		// 获取ip和port然后调用脚本来扩大
		if (used * 2 > max) {
			// 增大这个redis到max
		} else {
			// 增大这个redis到used*2；
		}
		System.out.println("增大这个redis");
	}

	private void doContract(double used, double min) {
		if (used / 2 < min) {
			// 缩小这个redis到min
		} else {
			// 缩小这个redis到used/2
		}

		System.out.println("缩小这个redis");
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
