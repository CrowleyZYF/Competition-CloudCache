package emem.cacheserver.controllers

import emem.cacheserver.clients.JedisCacheClient
import emem.cacheserver.core.CacheConfig
import emem.cacheserver.core.ServerConfig

/**
 * Created by hello on 14-11-22.
 */
@Route('/token')
class TokenController {

    private final def cacheConfig = CacheConfig.getInstance()

    def set(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'host', 'port:Integer'], rq, res)
        if(!params) return;

        ServerConfig.tokenDB.tokens.save(['_id': params.token, 'host': params.host, 'port': params.port])
        cacheConfig.setCacheClient(params.token, new JedisCacheClient(params.host, params.port))
    }

    def remove(rq, res) {
        def params = ControllerUtils.retrieveParams(['token'], rq, res)
        if(!params) return;

        ServerConfig.tokenDB.tokens.remove(['_id': params.token])
        cacheConfig.removeCacheClient(params.token)
    }

    def list(rq, res) {
        res.getWriter().print(cacheConfig.toString())
    }

}
