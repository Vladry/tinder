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
//        System.out.println("current RequestURI: " + requestURI);

        if (requestURI.equals("/favicon.ico")) {
            return;
        }

        if (this.ignoredPaths.contains(requestURI.split("/")[1])) {
            chain.doFilter(req, resp);
        }

        if (requestURI.equals("/gu")) {
//            System.out.println("отсылаем по getRequestDispatcher на  /gu");
            req.getRequestDispatcher("/gu").forward(req, resp);
        }

        HttpSession session = req.getSession(false);
        if (session != null) {
            System.out.println("Сессия обнаружена в LoginFilter");
            req.getRequestDispatcher(requestURI).forward(req, resp);
        } else {
            System.out.println("Сессия не обнаружена в LoginFilter");
            req.getRequestDispatcher("/login").forward(req, resp);
/*     !!! ни в коем случае не использовать в предыдущей строке вместо forward -инга (выше) строку ниже:
           templateEngine.render("login.ftl", new HashMap<>(), resp);
           иначе мы навсегда потеряем наш request в бесконечном круге:
           loginForm -> LoginFilter->LoginServlet->loginForm
           а, в этом request-е возможно сейчас и прилетели email/password в это место кода из login-формы

 */
        }
//        System.out.println("leaving LoginFilter at its end");
        chain.doFilter(request, response);
    }
}
