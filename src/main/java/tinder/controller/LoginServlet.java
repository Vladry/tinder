package tinder.controller;

import tinder.controller.servlet_system.TemplateEngine;
import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.http.*;
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
        System.out.println("This is LoginServlet (doGet)");
        templateEngine.render("login.ftl", new HashMap<String, Object>(), response);
//        System.out.println(userDao.findAll());
//        System.out.println(userDao.findByLoginPass("1","1"));
//        System.out.println(userDao.read(2L));
//         System.out.println(userDao.delete(4L));
//        System.out.println(userDao.create(new User(4L, "7", "7","Den",15)));
//        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        System.out.println("This is LoginServlet (doPost)");
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
        String servletPath = req.getServletPath().substring(1);
        if (session != null) {
            System.out.println("Сессия уже существует!!!!");
            User user = (User) session.getAttribute("user");
            data.put("user", user);
//            templateEngine.render("users.ftl", data, resp);
            String jumpToServlet = servletPath + ".ftl";
            templateEngine.render( jumpToServlet, data, resp);
        } else {
                email = req.getParameter("email");
                password = req.getParameter("password");
            System.out.println("email: " + email);
            System.out.println("password: " + password);

            //User user = userDao.findByLoginPass(email, password); TODO получить юзера из базы
            if (email != null && password != null
                    && email.equals("v@v") && password.equals("r")) {
                User user = new User(1L, email, password, "Петя Хлебалков", 13); // TODO разобраться где взять ID
                session = req.getSession(true);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(0);
                session.getAttribute("user");
                templateEngine.render("users.ftl", data, resp);

            } else {
                data.put("message", "wrong email or password");
                templateEngine.render("login.ftl", data, resp);
            }

        }
    }
}
