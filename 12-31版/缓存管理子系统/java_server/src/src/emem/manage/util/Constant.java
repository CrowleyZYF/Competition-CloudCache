package emem.manage.util;

public class Constant {

	// 监听端口
	public static final int NODE_PORT = 8190;
	public static final int REDIS_PORT = 8189;

	// 数据库
	public static final String DBHOST = "192.168.1.73";
	public static final int DBPORT = 27017;
	public static final String DBNAME_SYSTEM = "emem_system";
	public static final String DBNAME_NODE_INFO = "node_info";
	public static final String DBNAME_REDIS_INFO = "redis_info";
	public static final String NODE_COLLECTION = "node";
	public static final String REDIS_COLLECTION = "redis";

	// 线程池大小
	public static final int NODE_THREAD_NUMBER = 5;
	public static final int REDIS_THREAD_NUMBER = 5;

	// splitter
	public static final String REDIS_INFO_SPLITTER = ":";
}
