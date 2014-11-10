package emem.commons

import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

/**
 * Created by hello on 14-11-10.
 */
class Statics {
    static def pool = new JedisPool(new JedisPoolConfig(), "localhost")
}
