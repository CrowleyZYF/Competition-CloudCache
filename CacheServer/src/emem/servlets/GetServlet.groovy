package emem.servlets

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
        println "get data of $key"
    }

    @Override
    void doPost(HttpServletRequest req, HttpServletResponse res) {
        doGet(req, res)
    }
}
