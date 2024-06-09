package pt.ipp.isep.dei.esoft.project.ui.gui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is responsible for creating and configuring instances of the EmailService interface.
 */
public class EmailServiceController {
    // The name of the configuration file
    private static final String CONFIG_FILE = "config.properties";

    /**
     * Creates an instance of the EmailService interface based on the specified service type.
     * @param serviceType the type of the email service
     * @return an instance of the EmailService interface
     * @throws IllegalArgumentException if the specified service type is not supported
     */
    public static EmailService createEmailService(String serviceType) {
        return switch (serviceType) {
            case "Gmail" -> new GmailEmailService();
            case "DEI" -> new DEIEmailService();
            default -> throw new IllegalArgumentException("Unsupported email service type: " + serviceType);
        };
    }

    /**
     * Creates an instance of the EmailService interface based on the configuration file.
     * @return an instance of the EmailService interface or null if the configuration file could not be found or read
     */
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
