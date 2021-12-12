package tinder.controller;

import tinder.controller.servlet_system.AbstractFilter;
import tinder.controller.servlet_system.TemplateEngine;
import tinder.dao.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//@WebFilter(filterName = "/*")
public class LoginFilter extends AbstractFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig config) throws ServletException {
        System.out.println("this is init() of LoginFilter");
    }
    @Override
    public void destroy() {
        System.out.println("this is destroy() of LoginFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilter(request, response, chain);

 /*       HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        ArrayList<String> ignoredRouts = new ArrayList<String>(List.of("/assets", "/favicon.ico", "/logout"));
        if (ignoredRouts.contains(req.getServletPath())) {
            chain.doFilter(request, response);
        }

        System.out.println("This is LoginFilter");
        HttpSession session = req.getSession(false);
        String servletPath = req.getServletPath();
        if (session != null) {
            System.out.println("Сессия уже существует!!!!");
            System.out.println(servletPath);
            System.out.println("chain: " + chain);
            System.out.println("leaving LoginFilter. Jumping to requested page");
            req.getRequestDispatcher(servletPath).forward(req, resp);


        }
        else {
            System.out.println("jumping from LoginFilter to LoginServlet");
            req.getRequestDispatcher("/login").forward(req, resp);
        }*/
        System.out.println("This is doFilter of LoginFilter.  Leaving LoginFilter.");
    }
    }
