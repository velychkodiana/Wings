package com.wings.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pilot {
    private int pilotId;
    private String fullName;
}