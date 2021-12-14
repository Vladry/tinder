package tinder.controller;

import tinder.dao.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

//@WebFilter(filterName = "/*")
public class LoginFilter extends AbstractFilter implements Filter {
    TemplateEngine templateEngine;

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
        super.doFilter(request, response, chain);
        System.out.println("This is LoginFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String servletPath = req.getServletPath();

        if (session != null) {
            System.out.println("Сессия уже существует!!!!");
            int userId = (Integer) session.getAttribute("userId");
            req.setAttribute("userId", userId);
            req.getRequestDispatcher(servletPath).forward(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }

        /*
        // если бы делали athentication на куках, было бы:
        Cookie[] cookies = req.getCookies();
        Cookie jSessionCookie = Stream.of(cookies).filter(c -> c.getName().equals("JSESSIONID"))
                .findFirst().orElse(null);
        if (jSessionCookie != null) {
            System.out.println("jSessionCookieName: " + jSessionCookie.getName() +
                    "    jSessionCookieValue: " + jSessionCookie.getValue());
        } else {
            System.out.println("jSessionCookie = null");
        }
*/

        chain.doFilter(request, response);
    }
}
