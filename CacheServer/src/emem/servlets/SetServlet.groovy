package emem.servlets

import emem.commons.Statics

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

        def jedis;
        try {
            jedis = Statics.pool.getResource()
            jedis.set(key, value)
        } finally {
            jedis.close()
        }
    }

}
