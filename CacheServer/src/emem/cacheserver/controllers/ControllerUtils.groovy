package emem.cacheserver.controllers

import emem.cacheserver.core.CacheConfig

/**
 * Created by hello on 14-11-20.
 */
class ControllerUtils {

    private final static def cacheConfig = CacheConfig.getInstance()

    static def retrieveParams(paramNames, rq, res) {
        def params = [:]
        for(namePair in paramNames) {
            def tokens = namePair.split(':')

            def name = tokens[0]
            def value = rq.getParameter(name)
            if(!value) {
                res.sendError(400, "Missing parameter $name")
                return false
            }

            def type = tokens.size() > 1 ? tokens[1] : null
            if(type == "Integer") {
                value = value.toInteger()
            }

            params[name] = value
        }
        return params
    }

    static def getCacheClient(token, res) {
        def cacheClient = cacheConfig.getCacheClient(token)
        if(!cacheClient) {
            res.sendError(404, "Token[$token] not found")
            return false
        }
        return cacheClient
    }
}
