package tinder.controller;

import tinder.controller.servlet_system.TemplateEngine;

import javax.servlet.http.*;
import java.util.HashMap;

public class LogOutServlet extends HttpServlet {
    TemplateEngine templateEngine;

    public LogOutServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("This is LogOutServlet");
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();
        data.put("message", "you have signed out!");
        System.out.println("Leaving LogOutServlet");
        templateEngine.render("login.ftl", data, resp);
    }
}
