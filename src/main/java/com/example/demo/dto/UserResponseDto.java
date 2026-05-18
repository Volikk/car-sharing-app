package com.example.demo.dto;

import com.example.demo.model.User.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
