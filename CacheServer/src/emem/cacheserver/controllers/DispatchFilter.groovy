package emem.cacheserver.controllers

import emem.cacheserver.core.CacheConfig
import emem.cacheserver.core.ServerConfig

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by hello on 14-11-20.
 */
class DispatchFilter implements Filter {
    private final static logger = ServerConfig.getLogger()

    private final def mapping = [:]

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        logger.debug 'Dispatch filter init'

        def tokensConf = filterConfig.getServletContext().getRealPath('/WEB-INF/config/tokens.conf')
        CacheConfig.getInstance().init(new File(tokensConf))

        def pack = 'emem.cacheserver.controllers'
        def dir = new File(getClass().getResource('').getFile())
        dir.list().findAll {it.endsWith('.class')}
        .collect {pack + '.' + it.replaceFirst(/\.class/, '')}
        .collect {Class.forName(it)}
        .findAll {it.getAnnotation(Route)}
        .each {
            def path = it.getAnnotation(Route).value()
            mapping[path] = it.newInstance()
        }
    }

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        def rq = (HttpServletRequest)servletRequest
        def res = (HttpServletResponse)servletResponse

        def fullPath = rq.getServletPath()
        def index = fullPath.lastIndexOf('/')
        def path = fullPath[0..<index]?:'/'
        def method = fullPath.substring(index+1)

        def c
        def metaMethods

        if(!(c=mapping[path]) || !(metaMethods=c.respondsTo(method))) {
            res.sendError(404)
            return;
        }

        //TODO 方法执行过程中可能产生异常
        metaMethods[0].invoke(c, rq, res)

        filterChain.doFilter(servletRequest, servletResponse)
    }

    @Override
    void destroy() {
        logger.debug 'Dispatch filter destroy'

        def tokensConf = filterConfig.getServletContext().getRealPath('/WEB-INF/config/tokens.conf')
        CacheConfig.getInstance().store(new File(tokensConf))
    }
}
