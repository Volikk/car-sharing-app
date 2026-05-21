package com.example.demo.controller;

import com.example.demo.dto.RentalRequestDto;
import com.example.demo.dto.RentalResponseDto;
import com.example.demo.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public RentalResponseDto createRental(
            @RequestBody RentalRequestDto requestDto,
            Authentication authentication
    ) {
        return rentalService.add(requestDto, authentication.getName());
    }
}
