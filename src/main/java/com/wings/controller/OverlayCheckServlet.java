package com.wings.controller;

import com.wings.dao.FlightDao;
import com.wings.model.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class OverlayCheckServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(OverlayCheckServlet.class);
    private final FlightDao flightDao = new FlightDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Flight> allFlights = flightDao.findAll();
        List<String> overlays = new ArrayList<>();
        Set<Flight> flightsWithOverlay = new HashSet<>();

        for (int i = 0; i < allFlights.size(); i++) {
            for (int j = i + 1; j < allFlights.size(); j++) {
                Flight f1 = allFlights.get(i);
                Flight f2 = allFlights.get(j);

                if (f1.getDeparture().equals(f2.getDeparture()) && sharesCrewMember(f1, f2)) {
                    String msg = "⚠️ Overlay between " + f1.getFlightNumber() +
                            " and " + f2.getFlightNumber() + " at " + f1.getDeparture();
                    overlays.add(msg);
                    flightsWithOverlay.add(f1);
                    flightsWithOverlay.add(f2);
                    logger.warn(msg);
                }
            }
        }

        if (overlays.isEmpty()) {
            request.setAttribute("overlayMessage", "✅ No overlays were found.");
            logger.info("No overlays found.");
        } else {
            request.setAttribute("overlayMessage", "⚠️ Overlays found!");
            request.setAttribute("overlayList", overlays);
        }

        // виводимо тільки рейси з накладкою
        request.setAttribute("flights", new ArrayList<>(flightsWithOverlay));

        request.getRequestDispatcher("admin_page.jsp").forward(request, response);
    }

    private boolean sharesCrewMember(Flight f1, Flight f2) {
        return f1.getPilot1Name().equals(f2.getPilot1Name()) ||
                f1.getPilot2Name().equals(f2.getPilot2Name()) ||
                f1.getAttendant1Name().equals(f2.getAttendant1Name()) ||
                f1.getWayfinderName().equals(f2.getWayfinderName()) ||
                f1.getRadioOperatorName().equals(f2.getRadioOperatorName());
    }
}