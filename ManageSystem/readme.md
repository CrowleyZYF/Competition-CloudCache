###ManageSystem###

####缓存管理子系统

1. 搭建了两台虚拟机作为缓存服务器.
用户名：**emem** 密码： **emem**
> **192.168.1.113** 
> **192.168.1.206**
>     $ ssh emem@192.168.1.113

2. 缓存服务器配置
	- 统一用户名 **emem**
	- ***ssh*** 免秘钥登陆(与管理子系统)
	- ***Java*** 运行环境.（如果不用Java就不需要）
	- ***redis***

3. 缓存管理子系统（Java + Shell？）