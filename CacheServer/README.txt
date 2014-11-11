目前实现的功能是基于HTTP接口的set操作和get操作, 并实现了用户隔离. 
跑起来的前提是在本机打开两个redis服务器, 端口分别绑定到6379和6380
这两个redis分别给用户a和用户b使用. 
然后启动服务器, 并尝试在浏览器中输入:
localhost:8080/set?key=..&value=..&token=a或者b
localhost:8080/get?key=..&token=a或者b
