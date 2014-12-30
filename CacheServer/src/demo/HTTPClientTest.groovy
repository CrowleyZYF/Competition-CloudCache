import emem.cacheclient.HTTPCacheClient

def host = '192.168.1.73'

def port = 8080

def token = args.length > 1 ? args[0] : '192.168.1.113:6382';

def n = args.length > 2 ? args[1] : 100

HTTPCacheClient cacheClient = new HTTPCacheClient(host, port, token)

def begin, end;

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.set(i.toString(), i.toString())
}
end = System.currentTimeMillis()
println "Test set: ${(end - begin) / n}"

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.get(i.toString())
}
end = System.currentTimeMillis()
println "Test get: ${(end - begin) / n}"

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.hashSetAll(i + 'h', ['a': 'b', 'c': 'd'])
}
end = System.currentTimeMillis()
println "Test hashSetAll: ${(end - begin) / n}"

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.hashGetAll(i + 'h')
}
end = System.currentTimeMillis()
println "Test hashGetAll: ${(end - begin) / n}"

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.hashSet(i + 'h', 'a', 'e')
}
end = System.currentTimeMillis()
println "Test hashSet: ${(end - begin) / n}"

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.hashRemove(i + 'h', 'a')
}
end = System.currentTimeMillis()
println "Test hashRemove: ${(end - begin) / n}"

begin = System.currentTimeMillis()
for(int i = 0; i < n; i++) {
    cacheClient.hashGet(i + 'h', 'a')
}
end = System.currentTimeMillis()
println "Test hashGet: ${(end - begin) / n}"
