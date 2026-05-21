package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RentalRequestDto {
    private Long carId;
    private LocalDateTime returnDate;
}
