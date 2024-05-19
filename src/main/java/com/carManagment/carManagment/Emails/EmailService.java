package com.carManagment.carManagment.Emails;

public interface EmailService {
    void sendNotificationEmail(String recipientEmail, String subject, String message);
}
