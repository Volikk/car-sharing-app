package com.example.demo.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long rentalId;
    private String paymentType;
}
