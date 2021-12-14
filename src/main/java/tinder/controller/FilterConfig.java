package tinder.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "Filter",
//        urlPatterns = {"/assets/*.jpg", "/*"},
//        servletNames = "/login")
public class FilterConfig extends AbstractFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
//        ServletContext context = filterConfig.getServletContext();
//        FilterRegistration registration1 = context.getFilterRegistration("ViewReqDataFilter");
//        registration1.addMappingForUrlPatterns(null, true, "/assets/*.jpg", "/*");
//        registration1.addMappingForServletNames(null, true, "/login");
//
//        FilterRegistration registration2 = context.getFilterRegistration("LoginFilter");
//        registration2.addMappingForUrlPatterns(null, true, "/assets/*.jpg", "/*");
//        registration2.addMappingForServletNames(null, true, "/login");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Здесь код моего первого фильтра
        super.doFilter(request, response, chain);
    }

    @Override
    public void destroy(){}
}
