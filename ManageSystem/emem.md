###1.管理模块

 - 请求所有的redis实例: ***/manage/redis/getInstances***
	 - 返回一个[]
	 - 每个{}里的格式为：
		 - **id** ： ip:port 实例的标示符
		 - **identify** : run_i，redis自动分配的一串hash
		 - **name** : 创建实例时填入的管理员
		 - **status** : 0代表停止，1代表正在运行
		 - **type** ： redis或者memcached
		 - **area** : 区域
		 - **time** : 创建时间
		 - **used** ： 已使用了多少内存
		 - **all** ： 总共申请了多少内存

 - 创建一个新的Redis实例： ***/manage/redis/createInstance***
	 - 参数为：
		 - **name** ： 管理员
		 - **type** ： ‘redis’ / 'memcached'
		 - **area** ： 区域
		 - **all**  ： 申请多少内存
	 - 返回一个{}，是新建成功的实例，格式为：
	 	 - 参考create

 - 停止一个redis： ***/manage/redis/stopInstances***
	 - 参数：
		 - **ids** ： ip：port的数组
 
 - 启动一个redis： ***/manage/redis/startInstances***
	 - 参数：
		 - **ids** ： ip：port的数组

 - 删除一个redis：***/manage/redis/deleteInstances***
	 - 参数：
		 - **ids** ： ip：port的数组


###2.节点监控

 - 获取系统所有节点：***/monitor/node/getNodes***
	 - 参数：
	 - 返回一个[]:
	 - 每个{}内的格式为：
		 - **task_total**
		 - **task_running**
		 - **task_sleeping**
		 - **tasK_stopped**
		 - **task_zombie**
		 - **cpu_user**
		 - **cpu_system**
		 - **cpu_free**
		 - **cpu_io**
		 - **mem_total**
		 - **mem_used**
		 - **mem_free**
		 - **mem_buffers**
		 - **swap_total**
		 - **swap_used**
		 - **swap_free**
		 - **io_read**
		 - **io_write**
		 - **net_send**
		 - **net_receive**

 - 获取某个节点下所有的redis实例：***/monitor/node/getInstancesOnNode***
	 - 参数：
		 - id : ip
	 - 返回一个[];
	 - 格式为：
		 - **id** ： ip:port 实例的标示符
		 - **identify** : run_i，redis自动分配的一串hash
		 - **name** : 创建实例时填入的管理员
		 - **status** : 0代表停止，1代表正在运行
		 - **type** ： redis或者memcached
		 - **area** : 区域
		 - **time** : 创建时间
		 - **used** ： 已使用了多少内存
		 - **all** ： 总共申请了多少内存

 - 获取某个节点的所有历史监控信息：***/monitor/node/getHistoryAll***
	 - 参数：
		 - **id**：ip
	 - 返回一个[];
	 - 格式参考监控节点：
		 - **date**： 历史记录的时间
 
 - 获取某个节点在某个时间段的历史监控信息：***/monitor/node/getHistoryBetween***
	 - 参数：
		 - **id**：ip
		 - **start**：ISODate();
		 - **end**：ISODate();
		 - 比如：要获取12.22号到12.23号之间，start=new Date('2014-12-22')　end = new Date('2014-12-23')
	 - 返回一个[];
	 - 格式同上
 
 - 获取某个节点从某个时间点到现在的监控信息：***/monitor/node/getHistory***
	 - 参数：
		 - **id**:ip
		 - **time**: ISODate();

###3.Redis实例监控