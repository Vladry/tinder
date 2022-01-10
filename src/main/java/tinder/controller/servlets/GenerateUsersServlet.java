package tinder.controller.servlets;

import tinder.controller.unils.TemplateEngine;
import tinder.v_dao.UserDao_v;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class GenerateUsersServlet extends HttpServlet {
    UserDao_v userDao_hikari;
    TemplateEngine templateEngine;

    public GenerateUsersServlet(UserDao_v userDao_hikari, TemplateEngine templateEngine) {
        this.userDao_hikari = userDao_hikari;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        int id = 0;
        try {
            id = userDao_hikari.retrieveById(100).getId();
        } catch (Exception e) {
            System.out.println("error at getting userId from DB");
        }
        if (id == 100) {
            System.out.println("probationary users already exist in DB, terminating this operation!");
            System.out.println("idle 2 users from DB: " + userDao_hikari.retrieveById(100) + userDao_hikari.retrieveById(200));
        } else {
            System.out.println("generating users now!!!!");
            userDao_hikari.create("v@v",     "r", "Vlad", 35, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@2", "pw2", "Den_", 15, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@3", "pw3", "Hren", 77, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@4", "pw4", "Ben_", 53, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@5", "pw5", "Chlen", 62, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@6", "pw6", "Burlen", 28, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@7", "pw7", "Zapipen", 46, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@8", "pw8", "Ofigen", 136, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create("email@9", "pw9", "Prozren", 22, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            userDao_hikari.create( "email@10", "pw10", "Nadoen", 31, "https://upload.wikimedia.org/wikipedia/commons/6/6f/Beethoven.jpg");
            System.out.println("you've created 10 users in DB ");
            templateEngine.render("login.ftl", new HashMap<String, Object>(), response);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
