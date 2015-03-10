package emem.cacheserver.controllers

import emem.cacheserver.core.Stat

/**
 * Created by hello on 14-11-20.
 */
@Route("/")
class StringController {

    def set(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'value'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def result = it.set(params.key, params.value)
            Stat.insert(params.token, [key: params.key, op: 'set'])
            res.getWriter().print result
        }
    }

    def get(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def value = it.get(params.key)
            Stat.insert(params.token, [key: params.key, op: 'get', hit: value != null])
            res.getWriter().print value?:''
        }
    }

}
