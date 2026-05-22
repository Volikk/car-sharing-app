package com.example.demo.service;

public interface NotificationService {
    void sendRentalCreationMessage(String userEmail, String carModel, String dueDate);

    void sendRentalReturnMessage(Long rentalId, String carModel);

    void sendPaymentSuccessMessage(Long rentalId, java.math.BigDecimal amount);
}
