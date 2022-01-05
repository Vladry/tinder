package tinder.controller;

import tinder.dao.User;
import tinder.v_dao.LikesDao_v;
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
    private final UserDao_v userDao_hikari;
    private LikesDao_v likesDao_hikari;
    private int loggedUserId;
    private int currentCandidateId;
    private boolean rendering = false;
    private HttpSession session;

    public UsersServlet(UserDao_v userDao_hikari, LikesDao_v likesDao_hikari, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.userDao_hikari = userDao_hikari;
        this.likesDao_hikari = likesDao_hikari;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.rendering) { // -борьба с непонятным повторным вызовом этого метода doGet после каждого выполнения templateEngine.render()
            this.rendering = false;
            return;
        }
//        System.out.println("in doGet()");

        session = req.getSession(false);
        String Dislike = req.getParameter("Dislike");
        String Like = req.getParameter("Like");
        User loggedInUserId = (User) session.getAttribute("user");
        this.loggedUserId = loggedInUserId.getId();
        if (session.getAttribute("lastLiked") == null) {
            session.setAttribute("lastLiked", 1);
        }
        currentCandidateId = (Integer) session.getAttribute("lastLiked");
//        System.out.println("currentUser from session: " + currentCandidateId);


        if (this.currentCandidateId == this.loggedUserId) {
            this.currentCandidateId++;
        }

        User candidate = null;
//        System.out.println("current candidate before writing like into DB: " + currentCandidateId);
        if (Dislike != null && Dislike.equals("Dislike")) {
            likesDao_hikari.processLiked(loggedUserId, currentCandidateId -1, false);
        } else if (Like != null && Like.equals("Like")) {
            likesDao_hikari.processLiked(loggedUserId, currentCandidateId -1, true);
        }

        candidate = getNextValidCandidate();
        renderUsersPage(candidate, resp);
        this.currentCandidateId++;
        session.setAttribute("lastLiked", this.currentCandidateId);
    }

    private void renderUsersPage(User user, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);
        templateEngine.render("users.ftl", data, resp);
        this.rendering = true;
    }

    private User getNextValidCandidate() {
        //метод ищет в базе ближайшего юзера с заполненным полем "name" на случай фрагментированной базы.
        // либо возвращает счетчик id юзеров в исходное положение, если больше пользователей не нашлось.
        User user = null;
        int counter = 40;
        while (user == null || user.getName().isEmpty()) {
            if (--counter <= 0) break;
            user = userDao_hikari.retrieveById(this.currentCandidateId);
            if (user == null) {
                this.currentCandidateId++;
            }
        }
        if (user == null) {
            this.currentCandidateId = 1;
            user = userDao_hikari.retrieveById(this.currentCandidateId);
        }

        return user;
    }
}
