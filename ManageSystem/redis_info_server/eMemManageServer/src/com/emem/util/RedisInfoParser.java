package com.emem.util;

import java.util.HashMap;
import java.util.Map;

public class RedisInfoParser {

	private Map<String, String> _params = new HashMap<String, String>();

	private String[] _serverInfo = { "redis_version", "os", "process_id",
			"run_id", "tcp_port", "uptime_in_seconds", "uptime_in_days" };
	private String[] _serverDbName = { "version", "os", "pid", "rid", "port",
			"uptime_in_seconds", "uptime_in_days" };
	private String[] _clientInfo = { "connected_clients" };
	private String[] _clientDbName = { "clientNum" };
	private String[] _memoryInfo = { "used_memory", "used_memory_human",
			"used_memory_rss", "used_memory_peak", "used_memory_peak_human",
			"mem_fragmentation_ratio" };
	private String[] _memoryDbName = { "used_memory", "used_memory_human",
			"used_memory_os", "used_memory_peak", "used_memory_peak_human",
			"mem_fragmentation_ratio" };

	private String split = ":";

	public RedisInfoParser() {
		for (int i = 0; i < _serverInfo.length; i++) {
			_params.put(_serverInfo[i], _serverDbName[i]);
		}
		for (int i = 0; i < _clientInfo.length; i++) {
			_params.put(_clientInfo[i], _clientDbName[i]);
		}

		for (int i = 0; i < _memoryInfo.length; i++) {
			_params.put(_memoryInfo[i], _memoryDbName[i]);
		}
	}

	public String[] parse(String line) {
		return line.split(split);
	}

	public boolean isContain(String infoName) {
		if (_params.containsKey(infoName)) {
			return true;
		}
		return false;
	}

	public String getDbName(String infoName) {
		return _params.get(infoName);
	}
}
