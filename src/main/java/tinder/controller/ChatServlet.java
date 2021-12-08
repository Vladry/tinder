package tinder.controller;

import tinder.dao.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ChatServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public ChatServlet(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            data.put("user", user);
            templateEngine.render("chat.ftl", data, resp);
        } else {
            templateEngine.render("login.ftl", data, resp);
        }
    }
}
