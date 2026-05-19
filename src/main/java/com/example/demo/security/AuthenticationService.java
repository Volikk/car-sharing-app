package com.example.demo.security;

import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.dto.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
