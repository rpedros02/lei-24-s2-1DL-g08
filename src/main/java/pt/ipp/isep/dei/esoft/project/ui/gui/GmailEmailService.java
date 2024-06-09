package pt.ipp.isep.dei.esoft.project.ui.gui;

public class GmailEmailService implements EmailService {

    @Override
    public void sendEmail(String recipient, String subject, String message) {
        // Implement Gmail email sending logic here
        System.out.println("Sending email via Gmail: " + recipient + ", " + subject + ", " + message);
    }
}


