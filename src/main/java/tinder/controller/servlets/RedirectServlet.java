package tinder.controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().equals("/favicon.ico")) {return;}
        System.out.println("you've tried to access an endpoint which does not exist: ");
        System.out.println("req.getRequestURI():  " + req.getRequestURI());
        System.out.println("now redirecting to: '/users'");
        resp.sendRedirect("/users");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
