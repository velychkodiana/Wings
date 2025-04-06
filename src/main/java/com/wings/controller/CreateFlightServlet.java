package com.wings.controller;

import com.wings.dao.FlightDao;
import com.wings.model.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateFlightServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(CreateFlightServlet.class);
    private final FlightDao flightDao = new FlightDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String flightNumber = request.getParameter("flightNumber");
            String departureStr = request.getParameter("departure");
            String gate = request.getParameter("gate");
            String status = request.getParameter("status");
            String destination = request.getParameter("destination");

            String pilot1 = request.getParameter("pilot1");
            String pilot2 = request.getParameter("pilot2");
            String attendant1 = request.getParameter("attendant1");
            String attendant2 = request.getParameter("attendant2");
            String wayfinder = request.getParameter("wayfinder");
            String radio = request.getParameter("radio");

            LocalDateTime departure = LocalDateTime.parse(departureStr);

            Flight flight = Flight.builder()
                    .flightNumber(flightNumber)
                    .departure(departure)
                    .status(status)
                    .gate(gate)
                    .destination(destination)
                    .pilot1Name(pilot1)
                    .pilot2Name(pilot2)
                    .attendant1Name(attendant1)
                    .attendant2Name(attendant2)
                    .wayfinderName(wayfinder)
                    .radioOperatorName(radio)
                    .build();

            flightDao.createFlight(flight);
            logger.info("🛫 Flight '{}' created successfully", flightNumber);
            request.setAttribute("successMessage", "✅ Flight successfully created!");

        } catch (Exception e) {
            logger.error("❌ Error creating flight: {}", e.getMessage());
            request.setAttribute("errorMessage", "❌ Failed to create flight: " + e.getMessage());
        }

        request.getRequestDispatcher("despatchers_page.jsp").forward(request, response);
    }
}