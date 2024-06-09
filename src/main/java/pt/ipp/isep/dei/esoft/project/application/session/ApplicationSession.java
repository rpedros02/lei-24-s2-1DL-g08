package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class represents an ApplicationSession in the system.
 * It manages the current user session and application properties.
 */
public class ApplicationSession {
    /**
     * The AuthenticationRepository object.
     */
    private final AuthenticationRepository authenticationRepository;

    /**
     * The path to the configuration file.
     */
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config.properties";

    /**
     * The key for the company designation property.
     */
    private static final String COMPANY_DESIGNATION = "Company.Designation";

    /**
     * Constructs an ApplicationSession object.
     * It initializes the authenticationRepository from the Repositories singleton and loads the properties.
     */
    private ApplicationSession() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        Properties props = getProperties();
    }

    /**
     * Returns the current UserSession.
     *
     * @return the current UserSession
     */
    public UserSession getCurrentSession() {
        pt.isep.lei.esoft.auth.UserSession userSession = this.authenticationRepository.getCurrentUserSession();
        return new UserSession(userSession);
    }

    /**
     * Returns the application properties.
     * It loads the properties from the configuration file and returns them.
     *
     * @return the application properties
     */
    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(COMPANY_DESIGNATION, "MusgoSublime");

        // Read configured values
        try {
            InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }

    /**
     * The ApplicationSession singleton instance.
     */
    private static ApplicationSession singleton = null;

    /**
     * Returns the ApplicationSession singleton instance.
     * If the singleton is null, it creates a new ApplicationSession and assigns it to the singleton.
     *
     * @return the ApplicationSession singleton instance
     */
    public static ApplicationSession getInstance() {
        if (singleton == null) {
            synchronized (ApplicationSession.class) {
                singleton = new ApplicationSession();
            }
        }
        return singleton;
    }
}