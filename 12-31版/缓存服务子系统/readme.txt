ʵ�ֵĹ����б�:
1. ������HTTP���ݲ����ӿ�
    ���ݲ���������֧���ַ�����hash, �����й���ֵ������

2. ������token���ýӿ�
    token������Ҫ���û��������ϵͳ�ṩ�Ľӿ�.
    token���������ݷ���mongodb���ݿ���, �ĵ���ʽ: token, host, port

3. RMIЭ���֧��

4. JAR�ͻ���

5. ͳ�����ݵ�֧��
    �ֽ����пͻ���������ӵ�mongodb������ȥ��, Ŀǰ�����ݿ���
        IP: localhost, ���ݿ���: stat
    ���ݸ�ʽ: token, key, op, time
    ����op����: set, get, hash/setAll, hash/getAll, hash/set, hash/get, hash/remove, hash/size

��������:

1. ����, ��src�е�groovyԴ���javaԴ����뵽�ļ���WebContent/WEB-INF/classes

2. �Ƽ�����������:
    cd CacheServer/WebContent
    Linux��ִ��:
    	java -Djava.rmi.server.logCalls=true -cp 'WEB-INF/lib/jetty-server-8.1.8.jar:WEB-INF/classes' emem.cacheserver.ServerStart
    Windows��ִ��:
    	java -Djava.rmi.server.logCalls=true -cp 'WEB-INF/lib/jetty-server-8.1.8.jar;WEB-INF/classes' emem.cacheserver.ServerStart
    ��������-classpath������Linux��ð�ŷָ�, ��Windows�÷ֺŷָ�

3. ���԰�WebContentĿ¼����Servlet������, ��Jetty, Tomcat��. �Ƽ�ʹ����������Jetty
    ע������:
    1. ����ʱɾ��libĿ¼�µ�jetty jar
    2. Context�󶨵�/

4. ��token��stat�����ݿ����ʵ������, �������ÿ����޸��ļ�WebContent/WEB-INF/web.xml�еĲ���.
   ��DispatchFilter�µ�init-param��, �޸����е�param-value����

5. �ͻ��˲���:
    ���: jar cvf http-client.jar .
    java -cp '.:groovy-all-2.3.7.jar:http-client.jar' CacheClientDemo
    java -cp '.:groovy-all-2.3.7.jar:rmi-client.jar' RMIClientDemo