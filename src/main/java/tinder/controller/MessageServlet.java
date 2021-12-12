package tinder.controller;

import tinder.controller.servlet_system.TemplateEngine;
import tinder.dao.User;

import javax.servlet.http.*;
import java.util.HashMap;

public class MessageServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public MessageServlet(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("This is MessageServlet (doGet)");

//        Cookie[] cookies = req.getCookies();
//        Cookie jSessionCookie = Stream.of(cookies).filter(c -> c.getName().equals("JSESSIONID"))
//                .findFirst().orElse(null);
//        if (jSessionCookie != null) {
//            System.out.println("jSessionCookieName: " + jSessionCookie.getName() +
//                    "    jSessionCookieValue: " + jSessionCookie.getValue());
//        } else {
//            System.out.println("jSessionCookie = null");
//        }


        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        if (session != null){
            User user = (User) session.getAttribute("user");
            data.put("user", user);
            System.out.println("jumping to messages.ftl");
            templateEngine.render("messages.ftl", data, resp);
        } else {
            System.out.println("jumping to login.ftl");
            templateEngine.render("login.ftl", data, resp);
        }
    }
}
