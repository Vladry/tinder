package tinder.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "/*")
public class LoginFilter extends AbstractFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig config) throws ServletException {
    }
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        super.doFilter(request, response, chain);
        System.out.println("This is LoginFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        chain.doFilter(request, response);
    }
}
