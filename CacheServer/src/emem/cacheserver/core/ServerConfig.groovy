package emem.cacheserver.core

import com.gmongo.GMongo

/**
 * Created by hello on 14-11-26.
 */
class ServerConfig {

    final static logger = new SimpleLogger()

    final static tokenDB

    final static statDB

    static {
        def mongo = new GMongo()
        tokenDB = mongo.getDB('tokens')
        statDB = mongo.getDB('stat')
    }

}
