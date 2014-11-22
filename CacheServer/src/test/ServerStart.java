package test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by hello on 14-11-22.
 */
public class ServerStart {

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080; //default port
        String webAppDir = args.length > 1 ? args[1] : "WebContent"; //default web app dir
        String context = args.length > 2 ? args[2] : "/"; //default context

        Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        server.addConnector(connector);
        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(context);
        webApp.setResourceBase(webAppDir);
        webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        server.setHandler(webApp);

        System.out.println("Starting server at port " + port);
        server.start();
        server.join();
    }

}
