package com.example.demo.service;

import com.example.demo.dto.RentalRequestDto;
import com.example.demo.dto.RentalResponseDto;

public interface RentalService {
    RentalResponseDto add(RentalRequestDto requestDto, String email);
}
