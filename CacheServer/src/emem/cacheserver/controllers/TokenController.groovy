package emem.cacheserver.controllers

import emem.cacheserver.clients.JedisCacheClient
import emem.cacheserver.core.CacheConfig

/**
 * Created by hello on 14-11-22.
 */
@Route('/config/token')
class TokenController {

    private final def cacheConfig = CacheConfig.getInstance()

    def set(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'host', 'port:Integer'], rq, res)
        if(!params) return;

        cacheConfig.setCacheClient(params.token, new JedisCacheClient(params.host, params.port))
    }

    def remove(rq, res) {
        def params = ControllerUtils.retrieveParams(['token'], rq, res)
        if(!params) return;

        cacheConfig.removeCacheClient(params.token)
    }

    def list(rq, res) {
        res.getWriter().print(cacheConfig.toString())
    }

}
