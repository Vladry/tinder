package tinder.controller;

import tinder.controller.TemplateEngine;
import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class GenerateUsersServlet extends HttpServlet {
    UserDao userDao;
    TemplateEngine templateEngine;

    public GenerateUsersServlet(UserDao userDao, TemplateEngine templateEngine) {
        this.userDao = userDao;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println(userDao.findAll());
//        System.out.println(userDao.findByLoginPass("1","1"));
//        System.out.println(userDao.read(2L));
//        System.out.println(userDao.delete(4L));
        Long id = 0L;
        try {
            id = userDao.read(100L).getId();
        } catch (Exception e) {
            System.out.println("error at getting userId from DB");
        }
        if (id == 100) {
            System.out.println("probationary users already exist in DB, terminating this operation!");
            System.out.println("idle 2 users from DB: " + userDao.read(100L) + userDao.read(200L));
        } else {

            System.out.println(userDao.create(new User(100L, "v@v",     "r", "Vlad", 35, "")));
            System.out.println(userDao.create(new User(200L, "email2", "pw2", "Den", 15, "")));
            System.out.println(userDao.create(new User(300L, "email3", "pw3", "Hren", 77, "")));
            System.out.println(userDao.create(new User(400L, "email4", "pw4", "Ben", 53, "")));
            System.out.println(userDao.create(new User(500L, "email5", "pw5", "Chlen", 62, "")));
            System.out.println(userDao.create(new User(600L, "email6", "pw6", "Burlen", 28, "")));
            System.out.println(userDao.create(new User(700L, "email7", "pw7", "Zapipen", 46, "")));
            System.out.println(userDao.create(new User(800L, "email8", "pw8", "Ofigen", 136, "")));
            System.out.println(userDao.create(new User(900L, "email9", "pw9", "Prozren", 22, "")));
            System.out.println(userDao.create(new User(1000L, "email10", "pw10", "Nadoen", 31, "")));
            System.out.println("you've created 10 users in DB ");
            templateEngine.render("login.ftl", new HashMap<String, Object>(), response);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
