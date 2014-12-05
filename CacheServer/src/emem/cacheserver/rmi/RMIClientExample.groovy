package emem.cacheserver.rmi

import java.rmi.Naming

def port = 8088
def token = 'a'

def cacheClientFactory = Naming.lookup("rmi://localhost:$port/CacheClientFactory")
def cacheClient = cacheClientFactory.getInstance(token)
cacheClient.set('hello', 'world,aha')
println cacheClient.get('hello')