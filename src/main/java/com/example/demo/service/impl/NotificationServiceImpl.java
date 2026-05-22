package com.example.demo.service.impl;

import com.example.demo.notification.TelegramNotificationBot;
import com.example.demo.service.NotificationService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final TelegramNotificationBot telegramBot;

    @Override
    public void sendRentalCreationMessage(String userEmail, String carModel, String dueDate) {
        String message = String.format(
                "🚗 NEW RENTAL CREATED!\n"
                        + "User: %s\n"
                        + "Car: %s\n"
                        + "Should be returned by: %s",
                userEmail, carModel, dueDate
        );
        telegramBot.sendNotification(message);
    }

    @Override
    public void sendRentalReturnMessage(Long rentalId, String carModel) {
        String message = String.format(
                "🏁 CAR RETURNED!\n"
                        + "Rental ID: %d\n"
                        + "Car: %s\n"
                        + "Status: Successfully returned to the parking lot.",
                rentalId, carModel
        );
        telegramBot.sendNotification(message);
    }

    @Override
    public void sendPaymentSuccessMessage(Long rentalId, BigDecimal amount) {
        String message = String.format(
                "💰 PAYMENT SUCCESSFUL!\n"
                        + "Rental ID: %d\n"
                        + "Amount Paid: $%s\n"
                        + "Status: Confirmed via Stripe.",
                rentalId, amount.toString()
        );
        telegramBot.sendNotification(message);
    }
}
