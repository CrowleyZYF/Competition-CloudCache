package emem.cacheserver.controllers

import emem.cacheserver.core.CacheConfig

/**
 * Created by hello on 14-11-20.
 */
class ControllerUtils {

    private final static def cacheConfig = CacheConfig.getInstance()

    static def retrieveParams(paramNames, rq, res) {
        def params = [:]
        for(name in paramNames) {
            def value = rq.getParameter(name)
            if(!value) {
                res.sendError(400, "Missing parameter $name")
                return false
            }
            params[name] = value
        }
        return params
    }

    static def getCacheClient(token, res) {
        def cacheClient = cacheConfig.getCacheClient(token)
        if(!cacheClient) {
            res.sendError(500)
            return false
        }
        return cacheClient
    }
}
