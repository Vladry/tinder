package tinder;

import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tinder.controller.filters.FileServlet;
import tinder.controller.filters.LoginFilter;
import tinder.controller.filters.ViewReqDataFilter;
import tinder.controller.servlets.*;
import tinder.controller.unils.TemplateEngine;
import tinder.dao.*;

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
        UserDao_v userDao_hikari = new UserJdbcHikariDao();
        LikesDao_v likesDao_hikari = new LikesJdbcHikariDao();
        MessageDao_v msgDao_hikari = new MessageJdbcHikariDao();
//        System.out.println("now creating a test user: roman@gmail.com");
//        userDao_hikari.create("roman@gmail.com", "1", "test_name", 20, "test_url_photo");
//        System.out.println("getUserById:  22" + userDao_hikari.retrieveById(22));

        TemplateEngine templateEngine = new TemplateEngine();

        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);

        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");

        handler.addFilter(new FilterHolder(new ViewReqDataFilter()), "/logout",  EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine)), "/*", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new GenerateUsersServlet(userDao_hikari, templateEngine)), "/gu");
        handler.addServlet(new ServletHolder(new LoginServlet(userDao_hikari, templateEngine)), "/login");
        handler.addServlet(new ServletHolder(new MessageServlet(userDao_hikari, msgDao_hikari, templateEngine)), "/messages/*");
        handler.addServlet(new ServletHolder(new UsersServlet(userDao_hikari, likesDao_hikari, templateEngine)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(userDao_hikari, likesDao_hikari, templateEngine)),"/liked");
        handler.addServlet(new ServletHolder(new LogOutServlet(templateEngine)), "/logout");
        handler.addServlet(new ServletHolder(new RedirectServlet()), "/*");

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
