package tinder.controller;

import tinder.controller.servlet_system.TemplateEngine;
import tinder.dao.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class HomeServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public HomeServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("This is HomeServlet (doGet)");
        HashMap<String, Object> data = new HashMap<>();
/*        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            data.put("user", user);
            data.put("sessionId", session.getId());
            System.out.println("Leaving HomeServlet");
            templateEngine.render("home.ftl", data, resp);
        } else {
            System.out.println("Leaving HomeServlet");
            templateEngine.render("home.ftl", data, resp);
        }*/
        System.out.println("Leaving HomeServlet");
        templateEngine.render("home.ftl", data, resp);
    }
}
