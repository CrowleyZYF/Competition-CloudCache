package emem.cacheserver.core

import com.gmongo.GMongo
import emem.cacheserver.clients.JedisCacheClient

/**
 * Created by hello on 14-11-20.
 */
class CacheConfig {
    private static final def logger = ServerConfig.getLogger()

    private def tokenMapping = [:]
    private def db

    private CacheConfig() {
        def mongo = new GMongo()
        db = mongo.getDB('tokens')
    }

    def getCacheClient(String token) {
        def cacheClient = tokenMapping[token]
        if(!cacheClient) {
            def v = db.tokens.findOne(['token': token])
            if(!v) return null
            cacheClient = new JedisCacheClient(v.host, v.port)
            tokenMapping[token] = cacheClient
        }
        return cacheClient
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
        else {
            logger.error "Failed to store tokens config to file $file"
        }
    }

    private static def cacheConfig = new CacheConfig()

    static def getInstance() {
        return cacheConfig
    }

}
