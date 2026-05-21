package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.RentalRequestDto;
import com.example.demo.dto.RentalResponseDto;
import org.springframework.data.domain.Pageable;

public interface RentalService {
    RentalResponseDto add(RentalRequestDto requestDto, String email);

    RentalResponseDto returnCar(Long id);

    List<RentalResponseDto> getRentalsByUserIdAndStatus(Long userId, Boolean isActive, Pageable pageable);
}
