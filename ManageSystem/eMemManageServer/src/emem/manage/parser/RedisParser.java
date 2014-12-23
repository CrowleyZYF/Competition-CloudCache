package emem.manage.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import emem.manage.util.Constant;

public class RedisParser extends Parser {
	private String split = Constant.REDIS_INFO_SPLITTER;

	private String[] serverInfo = { "redis_version", "os", "arch_bits",
			"process_id", "run_id", "tcp_port", "uptime_in_seconds",
			"uptime_in_days" };
	private String[] clientInfo = { "connected_clients", "blocked_clients" };
	private String[] memoryInfo = { "used_memory", "used_memory_human",
			"used_memory_rss", "used_memory_peak", "used_memory_peak_human",
			"mem_fragmentation_ratio", "maxmemory" };
	private String[] cpuInfo = { "used_cpu_sys", "used_cpu_user",
			"used_cpu_sys_children", "used_cpu_user_children" };
	private String[] statsInfo = { "total_connections_received",
			"total_commands_processed", "instantaneous_ops_per_sec",
			"expired_keys", "evicted_keys", "keyspace_hits", "keyspace_misses" };

	private HashSet<String> all = new HashSet<String>();

	public RedisParser() {
		for (String s : serverInfo) {
			all.add(s);
		}

		for (String s : clientInfo) {
			all.add(s);
		}

		for (String s : memoryInfo) {
			all.add(s);
		}

		for (String s : cpuInfo) {
			all.add(s);
		}

	}

	public void parse(Scanner scanner) {
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			// System.out.println(line);
			if (line.isEmpty() || line.charAt(0) == '#') {
				continue;
			}
			String[] array = line.split(split);
			if (all.contains(array[0])) {
				result.put(array[0], array[1]);
			}
		}
	}
}
