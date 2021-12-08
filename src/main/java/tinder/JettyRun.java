package tinder;

import tinder.controller.*;
import tinder.dao.UserDao;
import tinder.dao.UserJdbcDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyRun {
    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        portStr = portStr == null ? "8088" : portStr;
        Integer port = Integer.parseInt(portStr);
        System.out.println("PORT: " + port);
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        final UserDao userDao = new UserJdbcDao();
        TemplateEngine templateEngine = new TemplateEngine();

        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);

//        handler.addFilter(new FilterHolder(new LoginFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");

        handler.addServlet(new ServletHolder(new LoginServlet(userDao, templateEngine)), "/login");
        handler.addServlet(new ServletHolder(new MessageServlet(templateEngine)), "/messages");
        handler.addServlet(new ServletHolder(new UsersServlet(templateEngine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(templateEngine)),"/liked");
        handler.addServlet(new ServletHolder(new HomeServlet(templateEngine)),"/");
        handler.addServlet(new ServletHolder(new LogOutServlet(templateEngine)), "/logout");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
