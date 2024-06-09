package pt.ipp.isep.dei.esoft.project.ui.gui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailServiceController {
    private static final String CONFIG_FILE = "config.properties";

    public static EmailService createEmailService(String serviceType) {
        return switch (serviceType) {
            case "Gmail" -> new GmailEmailService();
            case "DEI" -> new DEIEmailService();
            default -> throw new IllegalArgumentException("Unsupported email service type: " + serviceType);
        };
    }

    public static EmailService createEmailServiceFromConfig() {
        Properties properties = new Properties();
        try (InputStream input = EmailServiceController.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
                return null;
            }
            properties.load(input);
            String emailServiceType = properties.getProperty("email.service");
            return createEmailService(emailServiceType);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
