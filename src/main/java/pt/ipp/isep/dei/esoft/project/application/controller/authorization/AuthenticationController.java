package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * The AuthenticationController class is responsible for handling authentication-related operations.
 * It interacts with the AuthenticationRepository to perform operations such as login, logout,
 * and retrieve the roles of the currently logged in user.
 */
public class AuthenticationController {

    /**
     * Role constants for the AuthenticationController.
     * These constants represent the different roles a user can have in the system.
     */
    public static final String ROLE_ADMIN = "ADMINISTRATOR"; // Represents an Administrator role
    public static final String ROLE_EMPLOYEE = "EMPLOYEE"; // Represents an Employee role
    public static final String ROLE_HRM = "HRM"; // Represents a Human Resources Manager role
    public static final String ROLE_GSM = "GSM"; // Represents a General Services Manager role
    public static final String ROLE_COLLABORATOR = "COLLABORATOR"; // Represents a Collaborator role
    public static final String ROLE_VFM = "VFM"; // Represents a Vehicle Fleet Manager role

    /**
     * The AuthenticationRepository instance.
     * This instance is used to interact with the authentication repository,
     * allowing the controller to perform operations such as login, logout,
     * and retrieve the roles of the currently logged in user.
     */
    private final AuthenticationRepository authenticationRepository;

    /**
     * The constructor for the AuthenticationController.
     * It initializes the AuthenticationRepository instance.
     */
    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Performs the login operation.
     * It takes an email and a password as parameters and attempts to log in the user.
     * If the login is successful, it returns true. If the login fails, it returns false.
     *
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @return A boolean indicating the success of the login operation.
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Retrieves the roles of the currently logged in user.
     * This method checks if a user is currently logged in by calling the
     * `getCurrentUserSession().isLoggedIn()` method on the `authenticationRepository`.
     * If a user is logged in, it retrieves the user's roles by calling the
     * `getCurrentUserSession().getUserRoles()` method on the `authenticationRepository`.
     *
     * @return A list of UserRoleDTO objects representing the roles of the currently logged in user.
     *         If no user is logged in, this method returns null.
     */
    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Performs the logout operation.
     * It calls the `doLogout()` method on the `authenticationRepository` to log out the current user.
     */
    public void doLogout() {
        authenticationRepository.doLogout();
    }
}