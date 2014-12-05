package emem.cacheserver.controllers

import emem.cacheserver.core.CacheConfig
import emem.cacheserver.core.ServerConfig

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * Created by hello on 14-12-5.
 */
class ExpireFilter implements Filter {
    private final static logger = ServerConfig.getLogger()

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        logger.debug 'Expire filter init'
    }

    @Override
    void doFilter(ServletRequest rq, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug('Do expire filter')

        def expire = rq.getParameter('expire')
        if(expire) {
            def key = rq.getParameter('key')

            def token = rq.getParameter('token')
            def cacheClient = CacheConfig.getInstance().getCacheClient(token)
            if(!cacheClient) return

            cacheClient.operate {
                it.expire(key, expire.toInteger())
            }
        }
    }

    @Override
    void destroy() {
        logger.debug 'Expire filter destroy'
    }
}
