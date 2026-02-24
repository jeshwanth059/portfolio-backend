package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contact;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${admin.email}")
    private String adminEmail;

    public void sendContactNotification(Contact contact) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("Jeshwanthsagi@gmail.com");
            message.setTo(adminEmail);
            message.setSubject("New Contact Form Submission - Portfolio");
            
            String emailBody = String.format(
                "You received a new message from your portfolio contact form!\n\n" +
                "Name: %s\n" +
                "Email: %s\n" +
                "Message:\n%s\n\n" +
                "---\n" +
                "Sent from: Portfolio Contact Form",
                contact.getName(),
                contact.getEmail(),
                contact.getMessage()
            );
            
            message.setText(emailBody);
            mailSender.send(message);
            
            System.out.println("Email notification sent successfully!");
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
