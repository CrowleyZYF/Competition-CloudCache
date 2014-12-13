package emem.node.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser {
	private Map<String, Object> result = new HashMap<String, Object>();

	private String[] tasksKeyword = { "task_total", "task_running",
			"task_sleeping", "task_stopped", "task_zombie" };

	private String[] cpuKeyword = { "cpu_user", "cpu_system", "cpu_free",
			"cpu_io" };

	private String[] memKeyword = { "mem_total", "mem_used", "mem_free",
			"mem_buffers" };

	private String[] swapKeyword = { "swap_total", "swap_used", "swap_free" };

	private String[] ioKeyword = { "io_read", "io_write" };

	private String[] netKeyword = { "net_send", "net_recieve" };

	public void parse(Scanner scanner) {
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			// System.out.println(line);
			switch (line) {
			case "#top":
				parseTop(scanner);
				break;
			case "#io":
				parseIO(scanner);
				break;
			case "#net":
				parseNet(scanner);
				break;
			}
		}
	}

	private void parseTop(Scanner scanner) {
		System.out.println("top");
		for (int i = 0; i < 5; i++) {
			String line = scanner.nextLine();
			System.out.println(line);
			switch (i) {
			case 0:
				doTop(line);
				break;
			case 1:
				doTasks(line);
				break;
			case 2:
				doCpu(line);
				break;
			case 3:
				doMem(line);
				break;
			case 4:
				doSwap(line);
				break;
			default:
			}
		}
	}

	private void doTop(String line) {
		String loadaverages = line.split("load average:")[1];
		result.put("load_average",
				loadaverages.split(",")[0].replaceAll(" ", ""));
	}

	private void doTasks(String line) {
		String content = line.split(":")[1];
		String[] tasks = content.split(",");
		for (int i = 0; i < tasks.length; i++) {
			result.put(tasksKeyword[i], (tasks[i].trim().split(" "))[0]);
		}
	}

	private void doCpu(String line) {
		String content = line.split(":")[1];
		String[] cpus = content.split(",");
		String[] values = { cpus[0].trim().split(" ")[0],
				cpus[1].trim().split(" ")[0], cpus[3].trim().split(" ")[0],
				cpus[4].trim().split(" ")[0] };
		for (int i = 0; i < cpuKeyword.length; i++) {
			result.put(cpuKeyword[i], values[i]);
		}
	}

	private void doMem(String line) {
		String content = line.split(":")[1];
		String[] mems = content.split(",");
		for (int i = 0; i < memKeyword.length; i++) {
			result.put(memKeyword[i], mems[i].trim().split(" ")[0]);
		}
	}

	private void doSwap(String line) {
		String content = line.split(":")[1];
		String[] swaps = content.split(",");
		for (int i = 0; i < swapKeyword.length; i++) {
			result.put(swapKeyword[i], swaps[i].trim().split(" ")[0]);
		}
	}

	private void parseIO(Scanner scanner) {
		System.out.print("io");
		scanner.nextLine();
		scanner.nextLine();
		for (int i = 0; i < 8; i++) {
			scanner.nextInt();
		}
		result.put(ioKeyword[0], scanner.nextInt());
		result.put(ioKeyword[1], scanner.nextInt());
		System.out.println(result.get(ioKeyword[0]));
		System.out.println(result.get(ioKeyword[1]));
	}

	private void parseNet(Scanner scanner) {
		System.out.print("net");
	}

	public Map<String, Object> getResult() {
		return this.result;
	}
}
