package com.carManagment.carManagment.Emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendNotificationEmail(String recipientEmail, String subject, String message) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(message, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Error occurred while sending email:", e);
        }
    }
}
