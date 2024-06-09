package pt.ipp.isep.dei.esoft.project.ui.gui;

public class DEIEmailService implements EmailService {
    @Override
    public void sendEmail(String recipient, String subject, String message) {
        System.out.println("Sending email via DEI Service: " + recipient + ", " + subject + ", " + message);
    }
}