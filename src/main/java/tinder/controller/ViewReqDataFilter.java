package tinder.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "/*")
public class ViewReqDataFilter extends AbstractFilter implements Filter {

    public void init(javax.servlet.FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilter(request, response, chain);
        System.out.println("This is ViewReqDataFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String serverName = req.getServerName();
        System.out.println("requested:  serverName:  " + serverName);

        int localPort = req.getLocalPort();
        System.out.println("localPort:  localPort:  " + localPort);

        StringBuffer requestURL = req.getRequestURL();
        System.out.println("requested:  requestURL:  " + requestURL);

        String method = req.getMethod();
        System.out.println("requested:  method:  " + method);

        String requestURI = req.getRequestURI();
        System.out.println("requested:  requestURI:  " + requestURI);

        String servletPath = req.getServletPath();
        System.out.println("requested:  servletPath:  " + servletPath);

        String pathInfo = req.getPathInfo();
        System.out.println("requested:  pathInfo:  " + pathInfo);

        String queryString = req.getQueryString();
        System.out.println("requested:  queryString:  " + queryString);

        String contextPath = req.getContextPath();
        System.out.println("requested:  contextPath:  " + contextPath);

        ServletContext servletContext = req.getServletContext();
        System.out.println("requested:  servletContext:  " + servletContext);


        chain.doFilter(request, response);
    }
}
