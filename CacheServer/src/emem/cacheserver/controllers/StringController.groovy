package emem.cacheserver.controllers
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
            it.set(params.key, params.value)
        }
    }

    def get(rq, res) {
        def params = ControllerUtils.retrieveParams(['token', 'key'], rq, res)
        if(!params) return

        def cacheClient = ControllerUtils.getCacheClient(params.token, res)
        if(!cacheClient) return

        cacheClient.operate {
            def value = it.get(params.key)?:''
            res.getWriter().print(value)
        }
    }

}
