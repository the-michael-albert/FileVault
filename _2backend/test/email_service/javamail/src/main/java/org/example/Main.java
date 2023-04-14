package org.example;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        // Set up the SMTP server
        String smtpServer = "smtp.office365.com";
        int port = 587; // SSL port

        // Set up the login credentials
        String username = "contact@michaelalbert.one";
        String password = "GarfeildI$C00l";

        // Set up the email contents
        String sender = "contact@michaelalbert.one";
        String recipient = "recipient@example.com";
        String subject = "Example HTML email";
        String html = "<html><body><h1>This is an example HTML email!</h1><p>Here is some content:</p><ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul><img src=\"cid:image1\"></body></html>";

        // Load an image file to attach as an inline image
        File imageFile = new File("/Users/michaelalbert/Documents/GitHub/FileVault/_2backend/test/email_service/pymail/image.png");
        String imageCid = "image1";
        DataSource source = new FileDataSource(imageFile);
        DataHandler imageHandler = new DataHandler(source);

        // Set up the JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.enable", "true");

        // Set up the SMTP authentication
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create the message object and set its attributes
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        // Create the HTML body part
        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html, "text/html");

        // Create the image body part
        BodyPart imagePart = new MimeBodyPart();
        imagePart.setDataHandler(imageHandler);
        imagePart.setHeader("Content-ID", "<" + imageCid + ">");

        // Create the multipart message and add the parts
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(imagePart);

        // Set the multipart message as the content of the email
        message.setContent(multipart);

        // Send the email
        Transport.send(message);
    }

    public static String getPasswordFromConsole(){
        Console console = System.console();
        if (console == null) {
            System.err.println("No console found");
            System.exit(1);
        }
        char[] passwordArray = console.readPassword("Enter your password: ");
        return new String(passwordArray);
    }
}
