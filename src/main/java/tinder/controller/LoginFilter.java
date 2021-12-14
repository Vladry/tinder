package tinder.controller;

import tinder.controller.system.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "/*")
public class LoginFilter implements Filter {
    TemplateEngine templateEngine;

    public LoginFilter(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    @Override
    public void init(javax.servlet.FilterConfig config) throws ServletException {}
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("This is LoginFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String servletPath = req.getServletPath();
        System.out.println("current servletPath: " + servletPath);

        if (session != null) {
            System.out.println("Сессия уже существует!!!!");
            System.out.println(session);
            int userId = 0;
            try {
                userId = (Integer) session.getAttribute("userId");
                req.setAttribute("userId", userId);
            } catch (Exception e) {
                System.out.println("userId does not exist in session");;
            }
            req.getRequestDispatcher(servletPath).forward(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }

        chain.doFilter(request, response);
    }
}
