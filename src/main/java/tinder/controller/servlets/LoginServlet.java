package tinder.controller.servlets;

import tinder.controller.unils.TemplateEngine;
import tinder.domain.User;
import tinder.dao.UserDao_v;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    UserDao_v userDao_hikari;
    TemplateEngine templateEngine;
    int firstCandidateId;

    public LoginServlet(UserDao_v userDao_hikari, TemplateEngine templateEngine) {
        this.userDao_hikari = userDao_hikari;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        templateEngine.render("login.ftl", new HashMap<>(), response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        if (requestURI.equals("/favicon.ico")
                || requestURI.contains("assets")) {
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = userDao_hikari.retrieveByEmailPassword(email, password);
        } catch (Exception e) {
            System.out.println("error accessing DATABASE at getting login user");
        }

        if (user == null) {
            System.out.println("no such user " +
                    "\n generate users by URL: '/gu'");
            data.put("message", "login or password incorrect!");
            templateEngine.render("login.ftl", data, resp);
        } else {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(0);
            session.setAttribute("userId", user.getId());
            session.setAttribute("user", user);
            if(session.getAttribute("lastLiked") == null) {
                session.setAttribute("lastLiked" , 1);
            }
            firstCandidateId = (Integer) session.getAttribute("lastLiked");
            if (firstCandidateId == user.getId()) {firstCandidateId++;}
            User candidate = getFirstCandidate();
            session.setAttribute("lastLiked", firstCandidateId);
            data.put("loggedUser", user);
            data.put("candidate", candidate);
            templateEngine.render("users.ftl", data, resp);
        }
    }

    private User getFirstCandidate() {

        User user = null;
        int counter = 10;
        while (user == null || user.getName().isEmpty()) {
            if (--counter <= 0) break;
            user = userDao_hikari.retrieveById(firstCandidateId);
            if (user == null) {
                firstCandidateId++;
            }
        }
        if (user == null) {
            firstCandidateId = 1;
            user = userDao_hikari.retrieveById(firstCandidateId);
        }

        return user;
    }
}
