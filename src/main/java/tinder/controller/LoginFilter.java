package tinder.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

//@WebFilter(filterName = "/*")
public class LoginFilter extends AbstractFilter implements Filter {
    private final TemplateEngine templateEngine;

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
        System.out.println("This is LoginFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);

//        if (session == null) {
////            templateEngine.render("login.ftl", data, resp);
//            resp.sendRedirect("/");
//        }else
          chain.doFilter(request, response);
    }
}
