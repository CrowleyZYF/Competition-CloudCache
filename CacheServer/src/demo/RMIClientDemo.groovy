import java.rmi.Naming

def host = '192.168.1.73'

def port = 8080

def token = args.length > 1 ? args[0] : '192.168.1.113:6382';

def n = args.length > 2 ? args[1] : 1

def cacheClientFactory = Naming.lookup("rmi://$host:8088/CacheClientFactory")
def cacheClient = cacheClientFactory.getCacheClient(token)

for(i = 0; i < n; i++) {

    cacheClient.set('hello', 'world')
    println cacheClient.get('hello') == 'world'

    def hash = ['a': 'b', 'c': 'd']
    cacheClient.hashSetAll('hel', hash)
    println cacheClient.hashGetAll('hel') == hash

    cacheClient.hashSet('hel', 'a', 'e')
    println cacheClient.hashGet('hel', 'a') == 'e'

    cacheClient.hashRemove('hel', 'a')
    println cacheClient.hashGet('hel', 'a') == null
    println cacheClient.hashSize('hel') == 1

    cacheClient.get('hello', 1) //with expire
    //Thread.currentThread().sleep(1500)
    //println cacheClient.get('hello') == null

    def person = new Person(name: 'hello', age: 18)
    cacheClient.setObject('hello', person)
    println cacheClient.getObject('hello') == person

}