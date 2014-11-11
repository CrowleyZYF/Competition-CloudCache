package emem.servlets

import emem.servlets.Gold.TokenInvalidateException

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by hello on 14-11-4.
 */
class GetServlet extends HttpServlet {

    @Override
    void doGet(HttpServletRequest req, HttpServletResponse res) {
        def key = req.getParameter('key')
        def token = req.getParameter('token')

        def cacheConn = null;
        try {
            cacheConn = Gold.getCacheConnection(token)
            def value = cacheConn.get(key);
            res.getWriter().print(value)
        } catch(TokenInvalidateException ex) {
            res.sendError(400, "Not invalidate token: $token")
        } finally {
            cacheConn?.close()
        }
    }

    @Override
    void doPost(HttpServletRequest req, HttpServletResponse res) {
        doGet(req, res)
    }
}
