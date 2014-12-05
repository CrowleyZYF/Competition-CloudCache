package emem.cacheserver.clients

import redis.clients.jedis.Jedis

/**
 * Created by hello on 14-11-20.
 */
class JedisWrapper {

    private final Jedis jedis;

    JedisWrapper(jedis) {
        this.jedis = jedis
    }

    def set(key, value) {
        jedis.set(key, value)
    }

    def get(key) {
        jedis.get(key)
    }

    def hashSetAll(key, hash) {
        jedis.hmset(key, hash)
    }

    def hashGetAll(key) {
        jedis.hgetAll(key)
    }

    def hashSet(key, index, value) {
        jedis.hset(key, index, value)
    }

    def hashGet(key, index) {
        jedis.hget(key, index)
    }

    def hashRemove(key, index) {
        jedis.hdel(key, index)
    }

    def hashSize(key) {
        jedis.hlen(key)
    }

    def expire(key, expire) {
        jedis.expire(key, expire)
    }
}
