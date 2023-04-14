package org.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void main(String[] args) {
        // SMTP server configuration
        String smtpServer = "smtp.office365.com";
        int smtpPort = 587;
        String smtpEncryption = "STARTTLS";

        // Email account credentials
        String username = "contact@michaelalbert.one";
        String password = Main.getPasswordFromConsole();

        // Email message details
        String fromEmail = "contact@michaelalbert.one";
        String toEmail = "recipient@example.com";
        String subject = "Test Email";
        String body = "This is a test email sent using JavaMail API.";

        // Set SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set From, To, Subject and Body of the message
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
