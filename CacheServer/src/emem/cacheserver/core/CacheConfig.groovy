package emem.cacheserver.core

import emem.cacheserver.clients.JedisCacheClient

/**
 * Created by hello on 14-11-20.
 */
class CacheConfig {
    private static final def logger = ServerConfig.getLogger()

    private def tokenMapping = [:]

    private CacheConfig() {}

    def setCacheClient(token, cacheClient) {
        tokenMapping[token] = cacheClient
    }

    def getCacheClient(String token) {
        tokenMapping[token]
    }

    def removeCacheClient(token) {
        tokenMapping.remove(token)
    }

    def init(tokenCollection) {
        def tokenMapping = [:]
        tokenCollection.find().each {
            tokenMapping[it.token] = new JedisCacheClient(it.host, it.port.toInteger())
        }
        this.tokenMapping = tokenMapping
        logger.log "Init token mapping: \n${this.tokenMapping}"
    }

    def init(File file) {
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

    @Override
    String toString() {
        tokenMapping.entrySet().collect {"$it.key $it.value"}.join('\n')
    }

    private final static def cacheConfig = new CacheConfig()

    static def getInstance() {
        return cacheConfig
    }

}
