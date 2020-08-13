package com.appointment.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
    void sendMail(String toEmail, String subject, String message) throws MessagingException;
}
