package com.wings.filter;

import com.wings.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        //  URL дозволені без авторизації:
        boolean allowed =
                uri.endsWith("login.jsp") ||
                        uri.endsWith("/login") ||
                        uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/");

        if (user != null || allowed) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}