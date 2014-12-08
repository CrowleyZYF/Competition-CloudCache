package com.emem.manage;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.emem.model.Model;
import com.emem.util.RedisInfoParser;

public class SimpleManageServerHandle implements Runnable {

	private Socket incoming;
	private Map<String, Object> datas = new HashMap<String, Object>();

	public SimpleManageServerHandle(Socket s) {
		this.incoming = s;
	}

	public void run() {
		try {
			System.out.println("thread start");
			Scanner s = new Scanner(incoming.getInputStream());
			parseRawMessage(s);
			insertData(); // 将redis的监控信息存入mongo中
			doElastic();// 根据此redis的监控信息来实现弹性云缓存.
			System.out.println("thread down!");
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
			// if (parts[0].equals("bye")) {
			// insertData();
			// try {
			//
			// incoming.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			if (parser.isContain(parts[0])) {
				datas.put(parser.getDbName(parts[0]), parts[1]);
			}
		}
	}

	private void insertData() {
		Model m = new Model();
		datas.put("date", new Date());
		m.insert(this.datas);
		m.close();
	}

	/**
	 * 根据insert data 确定是否需要把redis分配的内存进行修改. 目前策略是依照ArrayList的伸缩方式. 上限是用户购买的内存大小.
	 * 下限通过系统的配置来确定.
	 */

	private void doElastic() {
		double ratio = Double.parseDouble((String) datas
				.get("mem_fragmentation_ratio"));
		if (ratio > 50) {
			doExtend();
		} else if (ratio < 25) {
			doContract();
		}
	}

	private void doExtend() {
		System.out.println("增大这个redis");
	}

	private void doContract() {
		System.out.println("缩小这个redis");
	}
}
