package com.example.demo.service;

import com.example.demo.dto.PaymentRequestDto;
import com.example.demo.dto.PaymentResponseDto;
import java.util.List;

public interface PaymentService {
    PaymentResponseDto createPaymentSession(PaymentRequestDto requestDto);

    List<PaymentResponseDto> getPaymentsByUserId(Long userId);

    void processSuccessPayment(String sessionId);
}
