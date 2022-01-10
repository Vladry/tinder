package tinder.controller;

import tinder.dao.User;
import tinder.v_dao.LikesDao_v;
import tinder.v_dao.UserDao_v;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    TemplateEngine templateEngine;
    UserDao_v userDao_hikari;
    LikesDao_v likesDao_hikari;
    private int loggedUserId;
    private ArrayList<User> contactList = null;

    public LikedServlet(UserDao_v userDao_hikari, LikesDao_v likesDao_hikari, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.userDao_hikari = userDao_hikari;
        this.likesDao_hikari = likesDao_hikari;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        User loggedUser = (User) session.getAttribute("user");
        loggedUserId = loggedUser.getId();
        contactList = likesDao_hikari.retrieveLikedUsers(loggedUserId);

            data.put("user", loggedUser);
            data.put("contactList", contactList);
            templateEngine.render("liked.ftl", data, resp);
    }
}
