package tinder.controller;

import tinder.dao.User;
import tinder.v_dao.UserDao_v;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserDao_v userDao_hikari;
    private int currentUser;
    private boolean rendering = false;
    private HttpSession session;

    public UsersServlet(UserDao_v userDao_hikari, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.userDao_hikari = userDao_hikari;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.rendering) {
            this.rendering = false;
            return;
        }
        System.out.println("in doGet()");

        session = req.getSession(false);
        String Dislike = req.getParameter("Dislike");
        String Like = req.getParameter("Like");
        User loggedInUserId = (User) session.getAttribute("user");
        int id = loggedInUserId.getId();
        if (session.getAttribute("lastLiked") == null) {
            session.setAttribute("lastLiked", 1);
        }
        currentUser = (Integer) session.getAttribute("lastLiked");
        System.out.println("currentUser from session: " + currentUser);


        if (this.currentUser == id) {
            this.currentUser++;
        }

        User user = null;
        if (Dislike == null && Like == null) {
            user = getExistingUser();
            currentUser--;
        } else if (Dislike != null && Dislike.equals("Dislike")) {
            user = getExistingUser();
            renderUsersPage(user, resp);
        } else if (Like != null && Like.equals("Like")) {
            user = getExistingUser();
            renderUsersPage(user, resp);
        }

        renderUsersPage(user, resp);
    }

    private void renderUsersPage(User user, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);
        templateEngine.render("users.ftl", data, resp);
        this.rendering = true;
    }

    private User getExistingUser() {
        //метод ищет в базе ближайшего юзера с заполненным полем "name" на случай фрагментированной базы.
        // либо возвращает счетчик id юзеров в исходное положение, если больше пользователей не нашлось.
        User user = null;
        int counter = 40;
        while (user == null || user.getName().isEmpty()) {
            if (--counter <= 0) break;
            user = userDao_hikari.retrieveById(this.currentUser);
            this.currentUser++;
        }
        if (user == null) {
            this.currentUser = 1;
            user = userDao_hikari.retrieveById(this.currentUser);
        }
        session.setAttribute("lastLiked", this.currentUser++);
        return user;
    }
}
