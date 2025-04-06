package com.wings.dao;

import com.wings.model.Flight;
import com.wings.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDao {

    public void createFlight(Flight flight) {
        String sql = "INSERT INTO flights (flight_number, departure, status, gate, " +
                "pilot_1_name, pilot_2_name, attendant_1_name, wayfinder_name, radio_operator_name, destination) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, flight.getFlightNumber());
            ps.setTimestamp(2, Timestamp.valueOf(flight.getDeparture()));
            ps.setString(3, flight.getStatus());
            ps.setString(4, flight.getGate());
            ps.setString(5, flight.getPilot1Name());
            ps.setString(6, flight.getPilot2Name());
            ps.setString(7, flight.getAttendant1Name());
            ps.setString(8, flight.getWayfinderName());
            ps.setString(9, flight.getRadioOperatorName());
            ps.setString(10, flight.getDestination());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                flights.add(Flight.builder()
                        .flightId(rs.getInt("flight_id"))
                        .flightNumber(rs.getString("flight_number"))
                        .departure(rs.getTimestamp("departure").toLocalDateTime())
                        .status(rs.getString("status"))
                        .gate(rs.getString("gate"))
                        .pilot1Name(rs.getString("pilot_1_name"))
                        .pilot2Name(rs.getString("pilot_2_name"))
                        .attendant1Name(rs.getString("attendant_1_name"))
                        .wayfinderName(rs.getString("wayfinder_name"))
                        .radioOperatorName(rs.getString("radio_operator_name"))
                        .destination(rs.getString("destination"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }
}