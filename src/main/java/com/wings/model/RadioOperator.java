package com.wings.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadioOperator {
    private int radioOperatorId;
    private String fullName;
}