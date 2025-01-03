package pos.Emails;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import pos.Login.cash;

public class ITContact {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SENDER_EMAIL = "supun200202@gmail.com";
    private static final String SENDER_PASSWORD = "unei tuzg pjeh smtn"; // Use a secure method to store this
    private static final String RECEIVER_EMAIL = "gunnery2002@gmail.com";

    public void sendEmail() {
        String name = cash.cashierer;
        String url = "https://drive.google.com/uc?export=view&id=1jqX_Gs4nZE_FyFhoaMq995xDcic7hMNp";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECEIVER_EMAIL));
            message.setSubject("Seeking for technical support");
            
             
            
            String content = "<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "<meta charset=\"UTF-8\">" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "<title>Booking Summary</title>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background-color: #f8f8f8; }" +
                    ".booking-summary { background-color: #fff; max-width: 400px; margin: 40px auto; " +
                    "border: 1px solid #e0e0e0; border-radius: 8px; padding: 20px; }" +
                    ".summary-title { text-align: center; font-size: 20px; font-weight: bold; }" +
                    ".ticket-details p, .pricing .row, .total p { margin: 0; font-size: 14px; }" +
                    ".total p { font-size: 16px; font-weight: bold; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class=\"booking-summary\">" +
                    "<h2 class=\"summary-title\">Requesting Assistance</h2>" +
                    "<div class=\"ticket-details\">" +
                    "<p><strong>" + name +" "+ "needs your help please assist him or her as soon as possible <br>~ Thank you ~"+"</strong></p>" +
                    "</div>" +
                    "<img src=\"" + url + "\" alt=\"Brand Logo\">" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
