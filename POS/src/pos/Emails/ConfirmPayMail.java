package pos.Emails;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import pos.Cashier_Dashboard.search;
import pos.Cashier_Dashboard.billdetails;
import pos.Product_Selection.frontdata;


public class ConfirmPayMail {
    public class target{
        public static String email = "";
    }
    
    String url = "https://drive.google.com/uc?export=view&id=1jqX_Gs4nZE_FyFhoaMq995xDcic7hMNp";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SENDER_EMAIL = "supun200202@gmail.com";
    private static final String SENDER_PASSWORD = "unei tuzg pjeh smtn"; // Use a secure method to store this
    private static final String RECEIVER_EMAIL = target.email;

    public void sendEmail() {
        int total = 0;
        int discounts = 0;
        int subtotal = 0;
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
            message.setSubject("Receipt for you purchase");

            String content = "<!DOCTYPE html>" +
        "<html lang=\"en\">" +
        "<head>" +
        "<meta charset=\"UTF-8\">" +
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
        "<title>Booking Summary</title>" +
        "<style>" +
        "body { font-family: Arial, sans-serif; background-color: #f8f8f8; }" +
        ".booking-summary { background-color: #fff; max-width: 300px; margin: 20px auto; " +
        "border: 1px solid #e0e0e0; border-radius: 8px; padding: 15px; }" +
        ".summary-title { text-align: center; font-size: 16px; font-weight: bold; margin-bottom: 10px; }" +
        ".ticket-details p { margin: 0; font-size: 12px; }" +
        ".items table { width: 100%; border-collapse: collapse; margin-top: 10px; }" +
        ".items th, .items td { padding: 5px; font-size: 12px; text-align: left; border-bottom: 1px solid #ddd; }" +
        ".items th { font-weight: bold; }" +
        ".total p { margin: 0; font-size: 12px; text-align: right; }" +
        ".footer { text-align: center; font-size: 12px; margin-top: 15px; }" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<div class=\"booking-summary\">" +
        "<h2 class=\"summary-title\">Invoice</h2>" +
        "<div class=\"ticket-details\">" +
        "<center><p><strong>Super Mart</strong></p></center>" +
        "<center><p><img src=\"" + url + "\" alt=\"Brand Logo\" width=\"100\"></p></center>" +
        "<p><strong>Bill Date:</strong> " + billdetails.date + "</p>" +
        "<p><strong>Start Time:</strong> " + search.time + "</p>" +
        "<p><strong>End Time:</strong> " + billdetails.etime + "</p>" +
        "<p><strong>Cashier Name:</strong> " + search.cashier + "</p>" +
        "</div>" +
        "<div class=\"items\">" +
        "<table>" +
        "<thead>" +
        "<tr>" +
        "<th>Product</th>" +
        "<th>Qty</th>" +
        "<th>Price</th>" +
        "<th>Sub Total</th>" +
        "</tr>" +
        "</thead>" +
        "<tbody>";

for (int i = 0; i < 100; i++) {
    if (billdetails.prodid[i] != null) {
        content += "<tr>" +
                "<td>" + frontdata.arr[i] + "</td>" +
                "<td>" + frontdata.selctedqty[i] + "</td>" +
                "<td>" + billdetails.prodprice[i] + "</td>" +
                "<td>" + (billdetails.prodprice[i] * frontdata.selctedqty[i]) + "</td>" +
                "</tr>";
        subtotal = billdetails.prodprice[i] * frontdata.selctedqty[i];
        total += subtotal;
        discounts = discounts + (billdetails.proddisc[i]* frontdata.selctedqty[i]);
    } else {
        break;
    }
}

content += "</tbody>" +
        "</table>" +
        "</div>" +
        "<div class=\"total\">" +
        "<p><strong>Total:</strong> " + total + "</p>" +
        "<p><strong>Total Savings:</strong> " + discounts + "</p>" +
        "</div>" +
        "<div class=\"footer\">" +
        "<p>Thank You</p>" +
        "<p>Software By: www.supunpos360.com</p>" +
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
