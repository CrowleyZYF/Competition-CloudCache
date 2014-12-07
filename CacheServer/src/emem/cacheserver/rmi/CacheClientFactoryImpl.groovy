package emem.cacheserver.rmi

import emem.cacheserver.core.CacheConfig
import emem.cacheserver.core.ServerConfig
import emem.common.rmi.CacheClient
import emem.common.rmi.CacheClientFactory

import java.rmi.server.UnicastRemoteObject

/**
 * Created by Hello on 2014/12/5.
 */
class CacheClientFactoryImpl implements CacheClientFactory {

    private final static def logger = ServerConfig.logger

    @Override
    CacheClient getInstance(String token) {
        logger.log "RMI: new cache client request from token $token"

        def cacheClient = CacheConfig.getInstance().getCacheClient(token)
        cacheClient = new CacheClientImpl(cacheClient)
        return UnicastRemoteObject.exportObject(cacheClient, 0)
    }
}
