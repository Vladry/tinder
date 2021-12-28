package tinder.controller;

import tinder.controller.TemplateEngine;

import javax.servlet.http.*;
import java.util.HashMap;

public class LogOutServlet extends HttpServlet {
    TemplateEngine templateEngine;

    public LogOutServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();
        data.put("message", "you have signed out!");
        templateEngine.render("login.ftl", data, resp);
    }
}
