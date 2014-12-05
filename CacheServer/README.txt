最新的工作进展是:

1. 实现了基本数据的操作接口, 包括基本字符串数据的get, set以及hash结构的支持

2. 更改了用户token的配置, 现在全部配置在mongodb数据库中

3. 增加接口的过期支持, 每个接口附加一个参数expire=seconds

4. 增加协议RMI的支持

5. 增加统计支持, 将每次请求的数据(key, time)添加到公用的mongodb数据库中

详情请见 docs/接口协议.txt

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

需要稍稍注意的工作:
1. 增加接口的过期支持
    如何获取expire
3. 增加协议HTTPS的支持
    https://docs.oracle.com/cd/E19148-01/820-0843/aeopq/index.html