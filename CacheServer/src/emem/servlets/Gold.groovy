package emem.servlets

import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

/**
 * Created by hello on 14-11-11.
 */
class Gold {
    static def pools = [
            'a': new JedisPool('localhost', 6379),
            'b': new JedisPool('localhost', 6380)
    ]

    static def getCacheConnection(String token) {
        def pool = pools[token]
        if(pool == null) throw new TokenInvalidateException()
        return pool.getResource()
    }

    static class TokenInvalidateException extends RuntimeException {

    }

}
