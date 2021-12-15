package tinder.controller;

import tinder.controller.TemplateEngine;
import tinder.dao.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LikedServlet extends HttpServlet {
    TemplateEngine templateEngine;

    public LikedServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            data.put("user", user);
            templateEngine.render("liked.ftl", data, resp);
        } else {
            templateEngine.render("login.ftl", data, resp);
        }
    }
}
