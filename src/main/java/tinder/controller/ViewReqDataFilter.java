package tinder.controller;

import tinder.controller.servlet_system.AbstractFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(filterName = "/*")
public class ViewReqDataFilter extends AbstractFilter implements Filter {

    public void init(javax.servlet.FilterConfig config) throws ServletException {
        System.out.println("this is init() of ViewReaDataFilter");
    }

    public void destroy() {
        System.out.println("this is destroy() of ViewReaDataFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilter(request, response, chain);
        HttpServletRequest req = (HttpServletRequest) request;
        ArrayList<String>  ignoredRouts = new ArrayList<String>(List.of("/users", "/home", "messages", "/assets"));
        System.out.println("This is ViewReqDataFilter");
        String servletPath = req.getServletPath();
        System.out.println("servletPath: "  + servletPath);
/*        if ( ignoredRouts.contains(servletPath) ) {
            System.out.println("this is an ingoredRout. Leaving ViewReqDataFilter");
            chain.doFilter(request, response);

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

//        String servletPath = req.getServletPath();
        System.out.println("requested:  servletPath:  " + servletPath);*/
       System.out.println("Leaving ViewReqDataFilter");
        chain.doFilter(request, response);
    }
}
