package com.wings;

import com.wings.dao.*;
import com.wings.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DaoTest {
    public static void main(String[] args) {

        //  1. Тест логіну
        UserDao userDao = new UserDao();
        Optional<User> user = userDao.login("admin", "admin123");
        user.ifPresentOrElse(
                u -> System.out.println("✅ Login success: " + u),
                () -> System.out.println("❌ Login failed")
        );

        // 2. Вивід усіх пілотів
        PilotDao pilotDao = new PilotDao();
        List<Pilot> pilots = pilotDao.findAll();
        System.out.println("👨‍✈️ All Pilots:");
        pilots.forEach(System.out::println);

        //  3. Вивід усіх бортпровідників
        AttendantDao attendantDao = new AttendantDao();
        List<Attendant> attendants = attendantDao.findAll();
        System.out.println("👩‍✈️ All Attendants:");
        attendants.forEach(System.out::println);

        // 4. Вивід усіх навігаторів
        WayfinderDao wayfinderDao = new WayfinderDao();
        List<Wayfinder> wayfinders = wayfinderDao.findAll();
        System.out.println("🛰️ All Wayfinders:");
        wayfinders.forEach(System.out::println);

        //5. Вивід всіх радіо операторів
        RadioOperatorDao radioDao = new RadioOperatorDao();
        List<RadioOperator> radios = radioDao.findAll();
        System.out.println("📻 All Radio Operators:");
        radios.forEach(System.out::println);

        // 6. Створення нового рейсу
        FlightDao flightDao = new FlightDao();
        Flight newFlight = Flight.builder()
                .flightNumber("WNG-2025")
                .departure(LocalDateTime.now().plusDays(1))
                .status("scheduled")
                .gate("A1")
                .pilot1Name("John Doe")
                .pilot2Name("Jane Smith")
                .attendant1Name("Emily Green")
                .wayfinderName("Tom Navigation")
                .radioOperatorName("Anna Radio")
                .destination("Kyiv")
                .build();

        flightDao.createFlight(newFlight);
        System.out.println("✈️ Flight created!");

        // 📋 7. Перевірка всіх рейсів
        List<Flight> flights = flightDao.findAll();
        System.out.println("📋 All Flights:");
        flights.forEach(System.out::println);
    }
}