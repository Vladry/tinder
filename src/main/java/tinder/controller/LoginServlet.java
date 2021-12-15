package tinder.controller;

import tinder.controller.TemplateEngine;
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
        templateEngine.render("login.ftl", new HashMap<>(), response);

//        System.out.println(userDao.findAll());
//        System.out.println(userDao.findByLoginPass("1","1"));
//        System.out.println(userDao.read(2L));
//        System.out.println(userDao.delete(4L));
//        System.out.println(userDao.create(new User(4L, "7", "7","Den",15)));
//        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
            if(requestURI.equals("/favicon.ico")) {return;}
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
//        user = new User(1L, "rvy", "r");

        if (user == null) {
            System.out.println("no such user " +
                    "\n generate users by URL: '/gu'");
            req.getRequestDispatcher("/login");
        }
        else {
            System.out.println("user idendified!");
            System.out.println("user " + user.getName() + " aged " + user.getAge() + " has logged in");
            HttpSession session = req.getSession(true);
            session.setMaxInactiveInterval(0);
            session.setAttribute("userId", user.getId());
            System.out.println("checking session content: " + session.getAttribute("userId"));
            data.put("user", user);
            String servletPath = req.getRequestURI().substring(1);
            System.out.println("user  id:"  + user.getName() + " has been authenticated");
            System.out.println("now jumping to location: " + servletPath);
            templateEngine.render(servletPath + ".ftl", data, resp);
        }
    }
}
