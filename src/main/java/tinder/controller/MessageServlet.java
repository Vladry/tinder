package tinder.controller;

import tinder.dao.User;

import javax.servlet.http.*;
import java.util.HashMap;

public class MessageServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public MessageServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);


        int contactId = Integer.parseInt(req.getRequestURI().split("/")[2]);
        System.out.println(contactId);

        User user = (User) session.getAttribute("user");
        data.put("user", user);
        templateEngine.render("messages.ftl", data, resp);
    }
}
