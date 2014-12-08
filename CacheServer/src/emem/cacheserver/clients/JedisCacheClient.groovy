package emem.cacheserver.clients

import redis.clients.jedis.JedisPool

/**
 * Created by hello on 14-11-20.
 */
class JedisCacheClient {

    private final def pool

    final def host
    final def port

    JedisCacheClient(host, port) {
        pool = new JedisPool(host, port)
        this.host = host
        this.port = port
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

    @Override
    String toString() {
        return "$host $port"
    }

}
