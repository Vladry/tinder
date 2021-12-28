package tinder.controller;

import tinder.dao.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

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
