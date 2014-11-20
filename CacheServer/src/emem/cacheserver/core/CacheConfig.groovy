package emem.cacheserver.core

import emem.cacheserver.clients.JedisCacheClient

/**
 * Created by hello on 14-11-20.
 */
class CacheConfig {

    private final tokenMapping = [
            'a': new JedisCacheClient('localhost', 6379),
            'b': new JedisCacheClient('localhost', 6380)
    ]

    def getCacheClient(String token) {
        return tokenMapping[token]
    }

    private static def cacheConfig = new CacheConfig()

    private CacheConfig() {}

    static def getInstance() {
        return cacheConfig
    }

}
