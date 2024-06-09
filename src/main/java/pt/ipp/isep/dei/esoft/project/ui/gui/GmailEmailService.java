package pt.ipp.isep.dei.esoft.project.ui.gui;

/**
 * This class implements the EmailService interface and provides a method for sending emails via Gmail.
 * It simulates the sending of an email by printing the details to the console.
 */
public class GmailEmailService implements EmailService {

    /**
     * Sends an email with the specified recipient, subject, and message via Gmail.
     * This implementation simulates the sending of an email by printing the details to the console.
     * @param recipient the recipient of the email
     * @param subject the subject of the email
     * @param message the message of the email
     */
    @Override
    public void sendEmail(String recipient, String subject, String message) {
        // Implement Gmail email sending logic here
        System.out.println("Sending email via Gmail: " + recipient + ", " + subject + ", " + message);
    }
}

