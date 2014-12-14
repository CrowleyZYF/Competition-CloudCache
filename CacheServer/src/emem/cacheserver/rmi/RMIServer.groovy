package emem.cacheserver.rmi

import emem.cacheserver.core.ServerConfig

import java.rmi.Naming
import java.rmi.registry.LocateRegistry
import java.rmi.server.UnicastRemoteObject

/**
 * Created by Hello on 2014/12/5.
 */
class RMIServer {
    private static final logger = ServerConfig.getLogger()

    static def start() {
        def port = 8088
        def registry = LocateRegistry.createRegistry(port)

        def cacheClientFactory = UnicastRemoteObject.exportObject(new CacheClientFactoryImpl(), 0)
        registry.bind('CacheClientFactory', cacheClientFactory)
        Naming.lookup("rmi://localhost:$port/CacheClientFactory") //TODO 预查一次

        logger.log "RMI Server started at port $port"
    }

}
