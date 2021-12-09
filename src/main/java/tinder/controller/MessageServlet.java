package tinder.controller;

import tinder.dao.User;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.stream.Stream;

public class MessageServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public MessageServlet(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

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
            templateEngine.render("messages.ftl", data, resp);
        } else {
            templateEngine.render("login.ftl", data, resp);
        }
    }
}
