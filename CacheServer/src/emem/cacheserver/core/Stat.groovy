package emem.cacheserver.core

/**
 * Created by hello on 15-3-10.
 */
class Stat {

    static def insert(token, data) {
        data.time = System.currentTimeMillis()
        ServerConfig.statDB[token].insert(data)
    }

}
