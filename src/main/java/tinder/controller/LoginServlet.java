package tinder.controller;

import org.eclipse.jetty.server.session.Session;
import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class LoginServlet extends HttpServlet {
    UserDao userDao;
    TemplateEngine templateEngine;

    public LoginServlet(UserDao userDao, TemplateEngine templateEngine) {
        this.userDao = userDao;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println(userDao.findAll());
//
//        System.out.println(userDao.findByLoginPass("vadim","1"));
//
//        System.out.println(userDao.read(2L));
//
//        System.out.println(userDao.delete(4L));
//
//        System.out.println(userDao.create(new User(4L, "Den@gmail.com", "1","Den",17,"https://res.cloudinary.com/dk88eyahu/image/upload/v1639425540/tinder/images_kah10o.png")));
//
//        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        String email = null;
        String password = null;
/*
        // если бы делали athentication на куках, было бы:
        Cookie[] cookies = req.getCookies();
        Cookie jSessionCookie = Stream.of(cookies).filter(c -> c.getName().equals("JSESSIONID"))
                .findFirst().orElse(null);
        if (jSessionCookie != null) {
            System.out.println("jSessionCookieName: " + jSessionCookie.getName() +
                    "    jSessionCookieValue: " + jSessionCookie.getValue());
        } else {
            System.out.println("jSessionCookie = null");
        }
*/

        HttpSession session = req.getSession(false);

        if (session != null) {
             System.out.println("Сессия уже существует!!!!");
            User user = (User) session.getAttribute("user");
            data.put("user", user);

            req.getRequestDispatcher("users").forward(req,resp);
//            templateEngine.render("users.ftl", data, resp);
        } else {

            try {
                email = req.getParameter("email");
                password = req.getParameter("password");

                System.out.println(email + " email");
                System.out.println(password + " password");

            } catch (NullPointerException e) {
                System.out.println("email or password not provided");
                ;
            }
            User user = userDao.findByLoginPass(email, password); //TODO получить юзера из базы

            if (email != null && password != null
                    && email.equals(user.getEmail()) && password.equals(user.getPassword())) {

                session = req.getSession(true);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60 * 5);
                session.getAttribute("user");

                resp.sendRedirect("/users");

            } else {
                data.put("message", "wrong email or password");
                templateEngine.render("login.ftl", data, resp);
            }

        }
    }
}
