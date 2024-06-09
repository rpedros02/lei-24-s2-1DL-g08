package pt.ipp.isep.dei.esoft.project.ui.gui;

/**
 * This class implements the EmailService interface and provides a method for sending emails.
 * It simulates the sending of an email by printing the details to the console.
 */
public class DEIEmailService implements EmailService {

    /**
     * Sends an email with the specified recipient, subject, and message.
     * This implementation simulates the sending of an email by printing the details to the console.
     * @param recipient the recipient of the email
     * @param subject the subject of the email
     * @param message the message of the email
     */
    @Override
    public void sendEmail(String recipient, String subject, String message) {
        System.out.println("Sending email via DEI Service: " + recipient + ", " + subject + ", " + message);
    }
}