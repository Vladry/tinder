package tinder.controller;

import tinder.dao.LikedDao;
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
    LikedDao likedDao;

    public LikedServlet(UserDao userDao, LikedDao likedDao, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.userDao = userDao;
        this.likedDao = likedDao;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        data.put("users", likedDao.findLikedUsers((Long)session.getAttribute("userId") ) );
        templateEngine.render("liked.ftl", data, resp);
    }
}
