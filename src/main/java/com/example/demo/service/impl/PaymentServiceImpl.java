package com.example.demo.service.impl;

import com.example.demo.dto.PaymentRequestDto;
import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.mapper.PaymentMapper;
import com.example.demo.model.Car;
import com.example.demo.model.Payment;
import com.example.demo.model.Rental;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final BigDecimal FINE_MULTIPLIER = BigDecimal.valueOf(1.5);

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final PaymentMapper paymentMapper;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Override
    @Transactional
    public PaymentResponseDto createPaymentSession(PaymentRequestDto requestDto) {
        Rental rental = rentalRepository.findById(requestDto.getRentalId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find rental by id: "
                        + requestDto.getRentalId()));

        BigDecimal amount = calculateAmount(rental, requestDto.getPaymentType());

        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8080/payments/success?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl("http://localhost:8080/payments/cancel")
                    .addLineItem(SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)
                            .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd")
                                    .setUnitAmount(amount.multiply(BigDecimal.valueOf(100)).longValue())
                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                            .setName("Car Rental: " + rental.getCar().getModel())
                                            .setDescription("Payment type: " + requestDto.getPaymentType())
                                            .build())
                                    .build())
                            .build())
                    .build();

            Session session = Session.create(params);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setStatus(Payment.Status.PENDING);
            payment.setType(Payment.Type.valueOf(requestDto.getPaymentType()));
            payment.setAmount(amount);
            payment.setSessionUrl(session.getUrl());
            payment.setSessionId(session.getId());

            return paymentMapper.toDto(paymentRepository.save(payment));

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Stripe payment session", e);
        }
    }

    @Override
    public List<PaymentResponseDto> getPaymentsByUserId(Long userId) {
        return List.of();
    }

    @Override
    @Transactional
    public void processSuccessPayment(String sessionId) {
        Payment payment = paymentRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find payment by session id: " + sessionId));

        payment.setStatus(Payment.Status.PAID);
        paymentRepository.save(payment);
    }

    private BigDecimal calculateAmount(Rental rental, String type) {
        Car car = rental.getCar();
        long days;

        if (type.equals("PAYMENT")) {
            days = ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
            if (days <= 0) {
                days = 1;
            }
            return car.getDailyFee().multiply(BigDecimal.valueOf(days));
        } else if (type.equals("FINE")) {
            if (rental.getActualReturnDate() != null && rental.getActualReturnDate().isAfter(rental.getReturnDate())) {
                days = ChronoUnit.DAYS.between(rental.getReturnDate(), rental.getActualReturnDate());
                if (days <= 0) {
                    days = 1;
                }
                BigDecimal baseFine = car.getDailyFee().multiply(BigDecimal.valueOf(days));
                return baseFine.multiply(FINE_MULTIPLIER);
            }
            return BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }
}
