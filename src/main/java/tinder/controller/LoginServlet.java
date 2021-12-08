package tinder.controller;

import org.eclipse.jetty.server.session.Session;
import tinder.dao.User;
import tinder.dao.UserDao;

import javax.servlet.http.*;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    UserDao userDao;
    TemplateEngine templateEngine;

    public LoginServlet(UserDao userDao, TemplateEngine templateEngine){
        this.userDao = userDao;
        this.templateEngine = templateEngine;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);
        String email = null;
        String password = null;

        if (session != null){
            User user = (User) session.getAttribute("user");
            data.put("user", user);
            templateEngine.render("users.ftl", data, resp);
        } else {

            try {
                email = (String) req.getParameter("email");
                password = (String) req.getParameter("password");
            } catch (NullPointerException e) {
                System.out.println("email or password not provided");;
            }
            //User user = userDao.findByLoginPass(email, password); TODO получить юзера из базы
           if (email != null && password != null
                   && email.equals("v@v") && password.equals("r")) {
               User user = new User(1L, email, password, "Петя Хлебалков", 13); // TODO разобраться где взять ID
               session = req.getSession(true);
               session.setAttribute("user", user);
               Cookie cookie = new Cookie("sessionId", session.getId());
               resp.addCookie(cookie);
               templateEngine.render("users.ftl", data, resp);
           } else {
                data.put("message", "wrong email or password");
               templateEngine.render("login.ftl", data, resp);
           }

        }
    }
}
