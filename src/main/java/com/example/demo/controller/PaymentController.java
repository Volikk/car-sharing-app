package com.example.demo.controller;

import com.example.demo.dto.PaymentRequestDto;
import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponseDto createPaymentSession(@RequestBody PaymentRequestDto requestDto) {
        return paymentService.createPaymentSession(requestDto);
    }

    @GetMapping("/success")
    public String handleSuccess(@RequestParam("session_id") String sessionId) {
        paymentService.processSuccessPayment(sessionId);
        return "Payment was successful! You can return to the app.";
    }
}
