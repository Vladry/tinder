package tinder.controller;

import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    UserDao userDao;
    TemplateEngine templateEngine;

    public LoginServlet(UserDao userDao, TemplateEngine templateEngine) {
        this.userDao = userDao;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        templateEngine.render("login.ftl", new HashMap<>(), response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        if (requestURI.equals("/favicon.ico")
                || requestURI.contains("assets")) {
            return;
        }
        System.out.println("Now in doPost of LoginServlet");


        HashMap<String, Object> data = new HashMap<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("going to check user in userDao()");
        User user = null;
        try {
            user = userDao.findByLoginPass(email, password);
        } catch (Exception e) {
            System.out.println("error accessing DATABASE at getting login user");
        }


        if (user == null) {
            System.out.println("no such user " +
                    "\n generate users by URL: '/gu'");
            data.put("message", "login or password incorrect!");
            templateEngine.render("login.ftl", data, resp);
        } else {
            HttpSession session = req.getSession();
            System.out.println("user identified!");
            System.out.println("user " + user.getName() + " aged " + user.getAge() + " has logged in");
            session.setMaxInactiveInterval(0);
            session.setAttribute("userId", user.getId());
            session.setAttribute("user", user);
//            System.out.println("checking session content:  userId= " + session.getAttribute("userId"));

            data.put("user", user);
            System.out.println("user  id: " + user.getName() + " has been authenticated");
            templateEngine.render("messages.ftl", data, resp);
        }
    }
}
