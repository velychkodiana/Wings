package com.wings.controller;

import com.wings.dao.UserDao;
import com.wings.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> userOpt = userDao.login(username, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            logger.info("✅ User '{}' logged in as '{}'", user.getUsername(), user.getPosition());

            switch (user.getPosition()) {
                case "admin":
                    response.sendRedirect("admin_page.jsp");
                    break;
                case "dispatcher":
                    response.sendRedirect("despatchers_page.jsp");
                    break;
                default:
                    response.sendRedirect("schedule");
            }
        } else {
            logger.warn("❌ Login failed for username: {}", username);
            response.sendRedirect("login.jsp?error=true");
        }
    }
}