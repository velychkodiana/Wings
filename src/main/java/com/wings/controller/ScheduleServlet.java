package com.wings.controller;

import com.wings.dao.FlightDao;
import com.wings.model.Flight;
import com.wings.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class ScheduleServlet extends HttpServlet {

    private final FlightDao flightDao = new FlightDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) (session != null ? session.getAttribute("user") : null);

        if (user == null ||  "admin".equals(user.getPosition())  || "dispatcher".equals(user.getPosition())) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Flight> flights = flightDao.findAll();
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("schedule.jsp").forward(request, response);
    }
}