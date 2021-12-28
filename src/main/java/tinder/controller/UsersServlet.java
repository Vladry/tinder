package tinder.controller;

import tinder.dao.LikedDao;
import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserDao userDao;
    private final LikedDao likedDao;
    private int Count = 1;

    public UsersServlet(UserDao userDao, LikedDao likedDao, TemplateEngine templateEngine) {
        this.userDao = userDao;
        this.likedDao = likedDao;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User userSession = (User) session.getAttribute("user");

        if((Long)session.getAttribute("userId")==Count){
            Count++;
        }
        Date date = new Date();

        userSession.setLoginDate(date);
        userDao.update(userSession);

        HashMap<String, Object> data = new HashMap<>();
        if (session != null) {
            User user = userDao.read((long) Count);
            data.put("user", user);
            data.put("Count", Count);
            templateEngine.render("users.ftl", data, resp);
        } else {
            templateEngine.render("login.ftl", data, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();

        if (Count==userDao.findNumRaws()) {
            resp.sendRedirect("/users");
        }

        HttpSession session = req.getSession(false);
        User userSession = (User) session.getAttribute("user");

        if (likedDao.findMark((Long)session.getAttribute("userId"), userDao.read((long) Count).getId())) {
//        if (likedDao.findMark(userSession.getId(), userDao.read((long) Count).getId())) {
            likedDao.update((Long)session.getAttribute("userId"), userDao.read((long) Count).getId(), req.getParameter("Like") != null); //update
//            likedDao.update(userSession.getId(), userDao.read((long) Count).getId(), req.getParameter("Like") != null); //update
        } else{
            likedDao.create(userSession.getId(), userDao.read((long) Count).getId(), req.getParameter("Like") != null);
        }

        Count++;
        if(userSession.getId()==Count){
            Count++;
        }

        User user = userDao.read((long) Count);
        data.put("user", user);
        data.put("Count", Count);
        templateEngine.render("users.ftl", data, resp);
    }
}
