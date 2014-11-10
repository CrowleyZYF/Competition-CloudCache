package emem.servlets

import emem.commons.Statics

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

        def jedis;
        try {
            jedis = Statics.pool.getResource()
            def value = jedis.get(key);
            res.getWriter().print(value)
        } finally {
            jedis.close()
        }
    }

    @Override
    void doPost(HttpServletRequest req, HttpServletResponse res) {
        doGet(req, res)
    }
}
