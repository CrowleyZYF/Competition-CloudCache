package emem.cacheserver.controllers

import emem.cacheserver.core.Stat
import emem.common.data.LHData

/**
 * Created by hello on 14-11-20.
 */
@Route('/hash')
class HashController {

    def setAll(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'value'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def hash = LHData.hashFromString(params.value)
            def result = it.hashSetAll(params.key, hash)
            Stat.insert(params.token, [key: params.key, op: 'hash/setAll'])
            res.getWriter().print result
        }
    }

    def getAll(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def hash = it.hashGetAll(params.key)
            Stat.insert(params.token, [key: params.key, op: 'hash/getAll', hit: hash != null])
            res.getWriter().print LHData.hashToString(hash)
        }
    }

    def set(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'index', 'value'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def result = it.hashSet(params.key, params.index, params.value)
            Stat.insert(params.token, [key: params.key, op: 'hash/set'])
            res.getWriter().print result
        }
    }

    def get(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'index'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def value = it.hashGet(params.key, params.index)
            Stat.insert(params.token, [key: params.key, op: 'hash/get', hit: value != null])
            res.getWriter().print value?:''
        }
    }

    def remove(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key', 'index'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def result = it.hashRemove(params.key, params.index)
            Stat.insert(params.token, [key: params.key, op: 'hash/remove'])
            res.getWriter().print result
        }
    }

    def size(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def size = it.hashSize(params.key)
            Stat.insert(params.token, [key: params.key, op: 'hash/size'])
            res.getWriter().print size
        }
    }
}
