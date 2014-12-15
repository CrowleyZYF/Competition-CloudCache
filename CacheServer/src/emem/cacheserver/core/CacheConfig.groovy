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

    @Override
    String toString() {
        tokenMapping.entrySet().collect {"$it.key $it.value"}.join('\n')
    }

    private final static def cacheConfig = new CacheConfig()

    static def getInstance() {
        return cacheConfig
    }

}
