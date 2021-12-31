package tinder;

import org.eclipse.jetty.servlet.FilterHolder;
import tinder.controller.*;
import tinder.dao.LikedDao;
import tinder.dao.LikedJdbcDao;
import tinder.dao.UserDao;
import tinder.dao.UserJdbcDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.v_dao.UserDao_v;
import tinder.v_dao.UserJdbcHikariDao;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JettyRun {
    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        portStr = portStr == null ? "8088" : portStr;
        int port = Integer.parseInt(portStr);
        System.out.println("PORT: " + port);
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        final UserDao userDao = new UserJdbcDao();
        final LikedDao likedDao = new LikedJdbcDao();

        UserDao_v userDao_hikari = new UserJdbcHikariDao();
//        System.out.println("now creating a test user: roman@gmail.com");
//        userDao_hikari.create("roman@gmail.com", "1", "test_name", 20, "test_url_photo");
//        System.out.println("getUserById:  22" + userDao_hikari.retrieveById(22));

        TemplateEngine templateEngine = new TemplateEngine();

        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);

        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");

        handler.addFilter(new FilterHolder(new ViewReqDataFilter()), "/logout",  EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine)), "/*", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new LoginServlet(userDao, userDao_hikari, templateEngine)), "/login");
        handler.addServlet(new ServletHolder(new MessageServlet(templateEngine)), "/messages");
        handler.addServlet(new ServletHolder(new UsersServlet(userDao, userDao_hikari, likedDao, templateEngine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(userDao,likedDao,templateEngine)),"/liked");
        handler.addServlet(new ServletHolder(new LogOutServlet(templateEngine)), "/logout");
        handler.addServlet(new ServletHolder(new RedirectServlet()), "/*");

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
