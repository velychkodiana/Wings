package com.wings.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int userId;
    private String fullName;
    private String position;
    private String username;
    private String password;
}