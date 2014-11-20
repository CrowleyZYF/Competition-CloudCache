目前实现的功能是基于HTTP接口的set操作和get操作, 并实现了用户隔离. 
跑起来的前提是在本机打开两个redis服务器, 端口分别绑定到6379和6380
这两个redis分别给用户a和用户b使用. 
然后启动服务器, 并尝试在浏览器中输入:
localhost:8080/set?key=..&value=..&token=a或者b
localhost:8080/get?key=..&token=a或者b

安装好JAVA后可以从命令行启动了:
首先cd进入文件夹WebContent, 然后输入命令: 
java -cp WEB-INF/classes;WEB-INF/lib/jfinal-1.8-bin-with-src.jar;WEB-INF/lib/jetty-server-8.1.8.jar test.Main
上面是windows下的启动方式, linux下的启动方式如下:
java -cp WEB-INF/classes:WEB-INF/lib/jfinal-1.8-bin-with-src.jar:WEB-INF/lib/jetty-server-8.1.8.jar test.Main
