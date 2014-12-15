import java.rmi.Naming

def cacheClientFactory = Naming.lookup("rmi://localhost:8088/CacheClientFactory")
def cacheClient = cacheClientFactory.getCacheClient('a')

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
Thread.currentThread().sleep(1500)
println cacheClient.get('hello') == null