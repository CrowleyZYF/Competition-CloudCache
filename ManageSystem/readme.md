###ManageSystem###

####缓存管理子系统

1. 搭建了两台虚拟机作为缓存服务器.
用户名：**emem** 密码： **emem**
> **192.168.1.113** 
>
> **192.168.1.206**
>
>     $ ssh emem@192.168.1.113

2. 缓存服务器配置
	- 统一用户名 **emem**
	- ***ssh*** 免秘钥登陆(与管理子系统)
	- ***Java*** 运行环境.（如果不用Java就不需要）
	- ***redis***

3. 缓存管理子系统（Java + Shell？）

####模块

1. ***redis_info_server.*** redis监控服务***（Java编写）***（基本完成）
	
	1. 对于每个redis进行监控.
	> 通过shell编程把集群中所有的redis的info信息定期汇报到这个服务器来.
	> 可以参考：[Shell网络编程][1] 
	> 此服务器会把info信息进行解析整理.存入Mongo中.
	
	2. 弹性云缓存策略
	> 每当redis汇报一次info信息，根据redis汇报的info信息，判断当前redis是否繁忙。（ArrayList的伸缩算法） 调用相应的shell脚本更新此redis实例的大小.

2. ***cluster_info_server*** 集群监控服务***（Java）*** （节点信息格式不确定？还未做解析）

	1. 监控集群的链路状态.

	> shell定期汇报集群的节点信息，此服务器进行解析并存入Mongo中.

3. ***manage_server*** 运维界面的后台 ***（PHP）耦合度比较大的一个模块*** （依赖于前两个模块的信息，基本未开始！！！）

	1. 对于前台页面暴露ajax接口，包括：

		- redis的监控信息(缓存服务状态)
		- 集群的监控信息（包括服务器状态和网络链路状态）
		- 缓存配置信息的获取和修改.
		- 统计信息接口(由叶老师存入Mongo)（最后来做）
		- 预设的系统配置信息！（我们自己定义的，诸如报警阈值、初始redis内存大小...）
		
	2. 对内需要连接Mongo和Shell.
	
		> 1. 监控的信息由Mongo提供
		> 2. 配置时，如前台点击 “新建一个实例” 应该经过如下过程：
		>  - 前台ajax传来参数
		>  - 通过访问集群的信息，结合简单的负载均衡算法，为redis分配一个节点（ip）
		>  - 通过shell脚本在该节点上启动redis（启动初始的配置通过ajax传来的参数）
		>  - 启动成功后，在数据库redis实例表中插入一条记录，并通知服务子系统，改实例对应的token.
		>  - 把redis info的信息返回到前台.

4. 数据库
 
- Redis实例表


|_id|rid|version|os|pid|ip|port|memory_buy|memory_allot|token|
|----|----|----|----|----|----|----|----|----|----|
|ObjectId|redis id|redis version|os|process id|ip|port|购买的内存|现在分配的内存|token|


- redis info表

|_id|rid|connected_clients|used_memory|used_memory_human|used_memory_rss|used_memory_peak|used_memory_peak_human|mem_fragmentation_ratio|
|----|----|----|----|----|----|----|----|----|----|----|----|

- 节点表

|_id|ip|os|version|cpu|memory|
|----|----|----|----|----|----|

- 节点信息表 

|_id|ip|cpu_used|memory_used|io_ratio|network|alive|
|----|----|----|----|----|----|----|


[1]:http://www.cnblogs.com/chengmo/archive/2010/10/22/1858302.html
