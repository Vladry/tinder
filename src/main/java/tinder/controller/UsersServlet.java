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
    private User loggedUser;
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
        session = req.getSession(false);
        String Dislike = req.getParameter("Dislike");
        String Like = req.getParameter("Like");
        loggedUser = (User) session.getAttribute("user");
        this.loggedUserId = loggedUser.getId();
        if (session.getAttribute("lastLiked") == null) {
            session.setAttribute("lastLiked", 1);
        }
        currentCandidateId = (Integer) session.getAttribute("lastLiked");
        if (this.currentCandidateId == this.loggedUserId) {
            this.currentCandidateId++;
        }

        User candidate = null;
        candidate = getCurrentCandidate();
        if (Dislike == null && Like == null) {
            renderUsersPage(candidate, resp);
        }


        if (Dislike != null && Dislike.equals("Dislike")) {
            likesDao_hikari.processLiked(loggedUserId, currentCandidateId, false);
            renderNext(resp);
        } else if (Like != null && Like.equals("Like")) {
            likesDao_hikari.processLiked(loggedUserId, currentCandidateId, true);
            renderNext(resp);
        }
    }


    private void renderUsersPage(User candidate, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loggedUser", loggedUser);
        data.put("candidate", candidate);
        templateEngine.render("users.ftl", data, resp);
        this.rendering = true;
    }

    private void renderNext(HttpServletResponse resp){
        this.currentCandidateId++;
        session.setAttribute("lastLiked", this.currentCandidateId);
        User candidate = null;
        candidate = getCurrentCandidate();
        renderUsersPage(candidate, resp);
    }

    private User getCurrentCandidate() {
        //метод ищет в базе ближайшего юзера с заполненным полем "name" на случай фрагментированной базы.
        // либо возвращает счетчик id юзеров в исходное положение, если больше пользователей не нашлось.
        User user = null;
        int counter = 10;
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
