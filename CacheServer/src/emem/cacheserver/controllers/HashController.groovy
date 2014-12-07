package emem.cacheserver.controllers

import emem.common.data.LHData

/**
 * Created by hello on 14-11-20.
 */
@Route('/data/hash')
class HashController {

    def setAll(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'value'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def hash = LHData.hashFromString(params.value)
            res.getWriter().print it.hashSetAll(params.key, hash)
        }
    }

    def getAll(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def hash = it.hashGetAll(params.key)
            res.getWriter().print LHData.hashToString(hash)
        }
    }

    def set(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'index', 'value'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            res.getWriter().print it.hashSet(params.key, params.index, params.value)
        }
    }

    def get(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'index'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            res.getWriter().print it.hashGet(params.key, params.index)?:''
        }
    }

    def remove(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'index'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            res.getWriter().print it.hashRemove(params.key, params.index)
        }
    }

    def size(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            res.getWriter().print it.hashSize(params.key)
        }
    }
}
