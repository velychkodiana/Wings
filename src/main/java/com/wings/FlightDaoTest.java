package com.wings;

import com.wings.dao.FlightDao;
import com.wings.model.Flight;

import java.util.List;

public class FlightDaoTest {
    public static void main(String[] args) {
        FlightDao dao = new FlightDao();
        List<Flight> flights = dao.findAll();
        System.out.println("Flights found: " + flights.size());
        flights.forEach(System.out::println);
    }
}
