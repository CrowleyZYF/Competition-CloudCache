实现的功能列表:
1. 基本的HTTP数据操作接口
    数据操作的类型支持字符串和hash, 并配有过期值的设置

2. 基本的token配置接口
    token配置需要调用缓存服务子系统提供的接口.
    token的配置内容放在mongodb数据库里, 现在放置的数据库是
        IP: localhost, 数据库名: tokens
    数据格式: _id(token), host, port

3. RMI协议的支持

4. JAR客户端

5. 统计数据的支持
    现将所有客户端请求添加到mongodb数据中去了, 目前的数据库是
        IP: localhost, 数据库名: stat
    数据格式: token, key, time

构建步骤:

1. 首先, 将src中的groovy源码和java源码编译到文件夹WebContent/WEB-INF/classes

2. 推荐命令行启动:
    cd CacheServer
    Linux下执行:
    java -classpath WebContent/WEB-INF/classes:WebContent/WEB-INF/lib/jetty-server-8.1.8.jar test.ServerStart
    Windows下执行:
    java -classpath WebContent/WEB-INF/classes;WebContent/WEB-INF/lib/jetty-server-8.1.8.jar test.ServerStart
    区别在于-classpath参数下Linux以冒号分割, 而Windows用分号分割

3. 可以把WebContent目录部署到Servlet容器下, 如Jetty, Tomcat等. 推荐使用轻量级的Jetty
    注意两点:
    1. 发布时删除lib目录下的jetty jar
    2. Context绑定到/

4. 对token和stat的数据库可以实现配置, 配置设置可以修改文件WebContent/WEB-INF/web.xml中的参数.
   在DispatchFilter下的init-param里, 修改其中的param-value即可

需要稍稍注意的工作:
1. 增加接口的过期支持
    如何获取expire
2. 增加协议HTTPS的支持
    https://docs.oracle.com/cd/E19148-01/820-0843/aeopq/index.html
3. RMI协议
    Groovy拦截器