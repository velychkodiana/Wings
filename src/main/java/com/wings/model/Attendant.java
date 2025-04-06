package com.wings.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendant {
    private int attendantId;
    private String fullName;
}