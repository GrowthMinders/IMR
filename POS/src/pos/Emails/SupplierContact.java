package pos.Emails;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import pos.Connections;



public class SupplierContact {
     public class mail{
         public static String email = "";
         public static String product = "";
         public static int qty = 0;
     }
    
    String url = "https://drive.google.com/uc?export=view&id=1jqX_Gs4nZE_FyFhoaMq995xDcic7hMNp";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SENDER_EMAIL = "supun200202@gmail.com";
    private static final String SENDER_PASSWORD = "unei tuzg pjeh smtn"; // Use a secure method to store this
    private static final String RECEIVER_EMAIL = mail.email;
    
    public void sendEmail() {
        
            try {
               String query = "SELECT amount FROM reorder WHERE pname = ?";
               PreparedStatement sql = Connections.connect().prepareStatement(query);
                
               sql.setString(1, mail.product);
               ResultSet result = sql.executeQuery();

               if(result.next()){
                  mail.qty = result.getInt("amount");
               }
              

            }catch (Exception ex){
               ex.printStackTrace();
            }
     
        
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
            message.setSubject("Stock Re-ordering");

            String content = "<!DOCTYPE html>" +
                 "<html lang=\"en\">" +
                 "<head>" +
                 "<meta charset=\"UTF-8\">" +
                 "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                 "<title>Reorder Request</title>" +
                 "<style>" +
                 "body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
                 ".email-container { max-width: 600px; margin: 50px auto; background: #ffffff; border-radius: 8px; " +
                 "box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); overflow: hidden; }" +
                 ".header { background: linear-gradient(90deg, #4CAF50, #81C784); padding: 20px; text-align: center; color: #fff; }" +
                 ".header h1 { margin: 0; font-size: 24px; }" +
                 ".body { padding: 20px; }" +
                 ".body h2 { font-size: 20px; color: #333; }" +
                 ".body p { font-size: 16px; color: #555; line-height: 1.6; }" +
                 ".details { margin-top: 20px; border-top: 1px solid #e0e0e0; padding-top: 15px; }" +
                 ".details h3 { font-size: 18px; color: #4CAF50; margin-bottom: 10px; }" +
                 ".details p { margin: 5px 0; }" +
                 ".footer { background: #f9f9f9; text-align: center; padding: 15px; font-size: 14px; color: #777; }" +
                 ".footer a { color: #4CAF50; text-decoration: none; font-weight: bold; }" +
                 "</style>" +
                 "</head>" +
                 "<body>" +
                 "<div class=\"email-container\">" +
                 "<div class=\"header\">" +
                 "<h1>Reorder Request</h1>" +
                 "</div>" +
                 "<div class=\"body\">" +
                 "<h2>Dear Supplier,</h2>" +
                 "<p>We hope this email finds you well. We are reaching out to request a reorder of the following product:</p>" +
                 "<div class=\"details\">" +
                 "<h3>Order Details:</h3>" +
                 "<p><strong>Product Name:</strong> " + mail.product + "</p>" +
                 "<p><strong>Quantity:</strong> " + mail.qty + "</p>" +
                 "</div>" +
                 "<p>Please confirm the availability and expected delivery date at your earliest convenience. If you have any questions, feel free to contact us.</p>" +
                 "<p>Thank you for your prompt assistance!</p>" +
                 "</div>" +
                 "<div class=\"footer\">" +
                 "<p>Best regards,</p>" +
                 "<p><strong>Super Mart</strong></p>" +
                 "<img src=\"" + url + "\" alt=\"Brand Logo\">" +
                 "<p><a href=\"mailto:supun200202@gmail.com\">contact@yourcompany.com</a></p>" +
                 "</div>" +
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
