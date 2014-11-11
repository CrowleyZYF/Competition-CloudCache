package emem.servlets

import emem.servlets.Gold.TokenInvalidateException

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by hello on 14-11-4.
 */
class SetServlet extends HttpServlet {

    @Override
    void doGet(HttpServletRequest req, HttpServletResponse res) {
        doPost(req, res);
    }

    @Override
    void doPost(HttpServletRequest req, HttpServletResponse res) {
        def key = req.getParameter('key')
        def value = req.getParameter('value')
        def token = req.getParameter('token')

        def cacheConn = null;
        try {
            cacheConn = Gold.getCacheConnection(token)
            cacheConn.set(key, value)
        } catch(TokenInvalidateException ex) {
            res.sendError(400, "Not invalidate token: $token")
        } finally {
            cacheConn?.close()
        }
    }

}
