package emem.cacheserver.controllers

import com.gmongo.GMongo
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
class DataFilter implements Filter {
    private final static logger = ServerConfig.getLogger()

    private def db

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        logger.debug 'Data filter init'

        def mongo = new GMongo()
        db = mongo.getDB("stat")
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
            db[token].insert(['key': key, 'time': System.currentTimeMillis()])
        }
    }

    @Override
    void destroy() {
        logger.debug 'Data filter destroy'
    }
}
