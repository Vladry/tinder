package tinder.controller;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractFilter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();

        ArrayList<String> filtersChain = (ArrayList<String>) httpSession.getAttribute("filtersChain");

        if (filtersChain == null) {
            filtersChain = new ArrayList<>();
        }

        if (!filtersChain.contains(this.getClass().getSimpleName())) {
            filtersChain.add(this.getClass().getSimpleName());
        }

        httpSession.setAttribute("filtersChain", filtersChain);

        chain.doFilter(request, response);
    }
}