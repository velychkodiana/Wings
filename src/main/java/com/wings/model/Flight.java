package com.wings.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    private int flightId;
    private String flightNumber;
    private LocalDateTime departure;
    private String status;
    private String gate;

    private String pilot1Name;
    private String pilot2Name;
    private String attendant1Name;
    private String attendant2Name;
    private String wayfinderName;
    private String radioOperatorName;

    private String destination;
}