package emem.cacheserver.rmi

import emem.cacheserver.core.CacheConfig
import emem.common.rmi.CacheClient
import emem.common.rmi.CacheClientFactory

import java.rmi.server.UnicastRemoteObject

/**
 * Created by Hello on 2014/12/5.
 */
class CacheClientFactoryImpl implements CacheClientFactory {

    @Override
    CacheClient getInstance(String token) {
        def cacheClient = CacheConfig.getInstance().getCacheClient(token)
        cacheClient = new CacheClientImpl(cacheClient)
        UnicastRemoteObject.exportObject(cacheClient, 0)
    }
}
