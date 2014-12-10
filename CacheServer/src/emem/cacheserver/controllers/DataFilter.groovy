package emem.cacheserver.controllers

import emem.cacheserver.core.CacheConfig
import emem.cacheserver.core.ServerConfig

import javax.servlet.*

/**
 * Created by hello on 14-12-5.
 */
class DataFilter implements Filter {
    private final static logger = ServerConfig.getLogger()

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        logger.debug 'Data filter init'
    }

    @Override
    void doFilter(ServletRequest rq, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug('Do data filter')

        def key = rq.getParameter('key')
        def token = rq.getParameter('token')
        if(key && token) {
            //过期设置
            def expire = rq.getParameter('expire')
            if(expire) {
                def cacheClient = CacheConfig.getInstance().getCacheClient(token)
                if(!cacheClient) return
                cacheClient.operate {
                    it.expire(key, expire.toInteger())
                }
            }

            //统计
            ServerConfig.statDB[token].insert(['key': key, 'time': System.currentTimeMillis()])
        }
    }

    @Override
    void destroy() {
        logger.debug 'Data filter destroy'
    }
}
