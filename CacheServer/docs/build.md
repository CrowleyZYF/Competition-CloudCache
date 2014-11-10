CacheServer使用JAVA Servlet处理用户请求, 依据的都是Java Servlet的那一套. 只是作了稍许更改:
1. 源代码并不局限于JAVA, 而是囊括了基于JVM平台的各种脚本语言. 只要源代码能够被编译成JAVA的.class文件, 都是允许的. 我已经成功将Groovy集成进去了, 很轻松, 不用费多大劲. 需要引入jar包: groovy-all-xxx.jar.
2. 项目中通过编写一个main方法来启动servlet容器, 即不是通过服务器发布而是跑应用程序的方式作开发. 这里实现的思想是:
	导入两个包(jfinal和jetty), 其中jfinal只是用来辅助启动jetty的, 我们并不使用此框架. 另外通过jfinal的扫描模式可以实现热部署, 即项目代码的.class文件有更改就自动重新加载项目而不需重新启动jetty. 

代码组织:
首先会看到文件夹CacheServer, 这是缓存服务子系统的代码, 构建运行和文档的集结地. 
1. src文件夹用于放置项目的源代码, 其中包括.java, .groovy或其他. 
2. WebContent作为项目的发布文件夹, 就是Servlet能认识的那一套. 
3. 以上两点说明: 
	1. 源代码必须编译到WebContent/WEB-INF/classes文件夹
	2. 最好, jar包放置在WebContent/WEB-INF/libs文件夹
4. docs文件夹, 用于存放各种文档. 

构建所需:
1. JDK, 用于编译JAVA源代码
2. Groovy, 用于编译Groovy源代码
3. 运行的时候, 运行test.Main, 并将当前目录设置为WebContent
4. That's all, 并不依赖具体的IDE. 

Git约定:
在使用git时, 注意不要将个人的开发环境上传到远程仓库, 需要做的是在.gitignore里添加相应行

使用IDEA搭建环境:
虽然说约定不要上传开发环境相关的文件, 但是在远程仓库里依然上传了一个文件叫做CacheServer.iml. 这个文件是IDEA独享的, 用于包含项目所需的jar库. 所以, 若使用IDEA作为开发环境, 那么库依赖问题就不用操心了. 
关于IDEA使用的问题, 只要记住IDEA和WebStorm和PHPStorm是一家的, 它们的操作模式极其类似. 
1. 确保系统安装JDK, 并配置好PATH路径, IDEA很有可能依赖此编译JAVA源代码.
2. 确保系统安装Groovy, 并配置好PATH路径, IDEA很有可能依赖此编译Groovy源代码. 尝试使用groovyc编译groovy文件, java命令运行之. 
3. 下载IDEA13.1(选社区版即可)并安装
4. git clone, 你懂的. 
5. 将CacheServer文件夹导入到IDEA项目. 这里不用new project, 只需要open project即可. 不熟悉IDEA(以及WebStorm等)的就不好说能不能懂我的意思类, 下同. 
6. 配置编译的.class输出目录到WebContent/WEB-INF/classes: 
    右键单击项目->Open Module Settings->Modules->Paths->选中Use module compile output path->output path和Test output path配置成WebContent/WEB-INF/classes的绝对路径. 尤其注意的是,这里一定要配置成绝对路径, 我配置成相对路径最后不知道编译到哪去了. 
7. 配置运行参数: 
    菜单Run->Edit Configurations->下拉Defaults->Application->Main class填test.Main, Working directory配置成WebContent的绝对路径. 
8. 运行之或者DEBUG之, 详见Run菜单或者相应快捷键, 选中Main跑即可. 
9. 由于JFinal为Jetty提供了热部署, 此时当源代码有改动时, 只需快捷键Ctrl+F9重新编译之, 则正在运行的Jetty会重新加载项目, 无须重新启动. 
10. done! Well done!

发布说明:
这里的三个jar:
1. groovy.jar作为跑Groovy代码的依赖应该不能去掉.
2. jetty-server.jar和jfinal.jar作为在应用程序中启动servlet服务器, 在真正发布时是不能存在的. 到时应该启动jetty服务器, 将项目发布在jetty服务器下跑起来. 更何况, 一个jfinal.jar, 作为一个JAVA框架, 居然只是用来辅助启动jetty以及支持.class改动扫描, 未免太搞了. 
