package tinder.controller;

import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    TemplateEngine templateEngine;
    UserDao userDao;

    public LikedServlet(UserDao userDao, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.userDao = userDao;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("users",userDao.findAll());
        System.out.println(data.get("users"));
//        HttpSession session = req.getSession(false);
//        if (session != null){
//            User user = (User) session.getAttribute("user");
//            data.put("user", user);
            templateEngine.render("users.ftl", data, resp);
//        } else {
//            templateEngine.render("login.ftl", data, resp);
//        }
    }
}
