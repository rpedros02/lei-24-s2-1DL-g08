package pt.ipp.isep.dei.esoft.project.ui.gui;

public interface EmailService {
    /**
     * Sends an email to the specified recipient.
     *
     * @param recipient The email address of the recipient.
     * @param subject   The subject of the email.
     * @param message   The content of the email message.
     */
    void sendEmail(String recipient, String subject, String message);
}
