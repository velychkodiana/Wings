package com.wings.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wayfinder {
    private int wayfinderId;
    private String fullName;
}
