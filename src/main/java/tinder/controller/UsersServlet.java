package tinder.controller;

import tinder.dao.User;
import tinder.v_dao.UserDao_v;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private int currentUser = 0;

    public UsersServlet(UserDao_v userDao_hikari, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            data.put("user", user);
            System.out.println("user to ftl: " + data.get("user"));
            templateEngine.render("users.ftl", data, resp);
        } else {
            templateEngine.render("login.ftl", data, resp);
        }
    }
}
