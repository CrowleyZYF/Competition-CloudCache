package emem.cacheserver.rmi

import java.rmi.Naming

def port = 8088
def token = 'a'

def cacheClientFactory = Naming.lookup("rmi://localhost:$port/CacheClientFactory")
def cacheClient = cacheClientFactory.getInstance(token)

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