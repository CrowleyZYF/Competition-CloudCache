package emem.cacheserver.core

import emem.cacheserver.clients.JedisCacheClient

/**
 * Created by hello on 14-11-20.
 */
class CacheConfig {

    private def tokenMapping = [:]

    def setCacheClient(token, client) {
        tokenMapping[token] = client
    }

    def getCacheClient(String token) {
        return tokenMapping[token]
    }

    def removeCacheClient(token) {
        tokenMapping.remove(token)
    }

    @Override
    String toString() {
        tokenMapping.entrySet().collect {"$it.key $it.value"}.join('\n')
    }

    def init(file) {
        if(!file.exists()) return
        try {
            def tokenMapping = [:]
            file.eachLine {
                def items = it.split(' ')
                tokenMapping[items[0]] = new JedisCacheClient(items[1], items[2].toInteger())
            }
            this.tokenMapping = tokenMapping
        } catch(e) {
            e.printStackTrace()
        }
    }

    def store(file) {
        if(file.getParentFile().mkdirs()) file.write(toString())
        //TODO 保存tokens.conf失败
    }

    private static def cacheConfig = new CacheConfig()

    private CacheConfig() {}

    static def getInstance() {
        return cacheConfig
    }

}
