package emem.cacheserver.clients

import redis.clients.jedis.JedisPool

/**
 * Created by hello on 14-11-20.
 */
class JedisCacheClient {

    private final def pool

    JedisCacheClient(host, port) {
        pool = new JedisPool(host, port)
    }

    def operate(Closure closure) {
        def jedis
        try {
            jedis = pool.getResource()
            closure(new JedisWrapper(jedis))
        } finally {
            jedis.close()
        }
    }

}
