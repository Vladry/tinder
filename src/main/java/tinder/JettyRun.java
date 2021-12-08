package vlad;

import vlad.controller.*;
import vlad.dao.UserDao;
import vlad.dao.UserJdbcDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

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

        handler.addServlet(new ServletHolder(new HelloServlet(templateEngine)), "/hello");
        handler.addServlet(new ServletHolder(new LoginServlet(userDao, templateEngine)), "/");
        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
        handler.addServlet(new ServletHolder(new AboutServlet(templateEngine)), "/about");
        handler.addServlet(new ServletHolder(new TeamServlet(templateEngine)), "/team");
        handler.addFilter(new FilterHolder(new LoginFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));

//        handler.addServlet(new ServletHolder(new UserServlet(userDao)), "/users");

//        handler.addServlet(new ServletHolder(new LikeServlet(userDao)), "/liked");

//        handler.addServlet(new ServletHolder(new MessageServlet(messageDao, userDao)), "/messages/*");
//        handler.addFilter(MessageFilter.class, "/messages/*", EnumSet.of(DispatcherType.REQUEST));


//        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
