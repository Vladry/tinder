package tinder.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//@WebFilter(filterName = "/*")
public class LoginFilter implements Filter {
    TemplateEngine templateEngine;
    List<String> ignoredPaths = new ArrayList<>(List.of("assets", "favicon.ico"));

    public LoginFilter(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void init(javax.servlet.FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        System.out.println("This is LoginFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();
        System.out.println("current RequestURI: " + requestURI);

        if (this.ignoredPaths.contains(requestURI.split("/")[1]))
        {
//            System.out.println("this is an ignoredPath, now leaving LoginFilter by 'return'! ");
//            return;
            chain.doFilter(req, resp);
//            templateEngine.render("login.ftl", new HashMap<>(), resp);
        }
        if (requestURI.equals("/gu")){
//            System.out.println("отсылаем по getRequestDispatcher на  /gu");
            req.getRequestDispatcher( "/gu").forward(req, resp);
            chain.doFilter(req, resp);
        }

        HttpSession session = req.getSession(false);
        if (session != null) {
            System.out.println("Сессия уже существует");
            System.out.println(session);
            int userId = 0;
            try {
                userId = (Integer) session.getAttribute("userId");
                req.setAttribute("userId", userId);
            } catch (Exception e) {
                System.out.println("userId does not exist in session");;
            }
            req.getRequestDispatcher(requestURI).forward(req, resp);
        } else {
            System.out.println("Сессия не обнаружена");
            templateEngine.render("login.ftl", new HashMap<>(), resp);
        }

//        System.out.println("leaving LoginFilter at its end");
        chain.doFilter(request, response);
    }
}
