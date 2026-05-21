package com.example.demo.controller;

import com.example.demo.dto.RentalRequestDto;
import com.example.demo.dto.RentalResponseDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.RentalService;
import com.example.demo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final UserService userService;

    @PostMapping
    public RentalResponseDto createRental(
            @RequestBody RentalRequestDto requestDto,
            Authentication authentication
    ) {
        return rentalService.add(requestDto, authentication.getName());
    }

    @PostMapping("/{id}/return")
    public RentalResponseDto returnCar(@PathVariable Long id) {
        return rentalService.returnCar(id);
    }

    @GetMapping
    public List<RentalResponseDto> getRentals(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Boolean isActive,
            Pageable pageable,
            Authentication authentication
    ) {
        String email = authentication.getName();
        UserResponseDto currentUser = userService.getProfile(email);

        Long targetUserId;

        if (currentUser.getRole().name().equals("MANAGER") && userId != null) {
            targetUserId = userId;
        } else {
            targetUserId = currentUser.getId();
        }

        return rentalService.getRentalsByUserIdAndStatus(targetUserId, isActive, pageable);
    }
}
