package com.example.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentResponseDto {
    @org.springframework.data.annotation.Id
    private Long id;
    private String status;
    private String type;
    private Long rentalId;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amount;
}
