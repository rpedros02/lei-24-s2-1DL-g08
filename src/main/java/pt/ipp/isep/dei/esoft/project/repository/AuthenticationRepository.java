package pt.ipp.isep.dei.esoft.project.repository;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

/**
 * This class represents an AuthenticationRepository in the system.
 * It has one field: authenticationFacade, which is an instance of AuthFacade.
 */
public class AuthenticationRepository {
    /**
     * The AuthFacade instance used for authentication operations.
     */
    private final AuthFacade authenticationFacade;

    /**
     * Constructs an AuthenticationRepository object.
     * It initializes the authenticationFacade as a new AuthFacade.
     */
    public AuthenticationRepository() {
        authenticationFacade = new AuthFacade();
    }

    /**
     * Performs a login operation with the specified email and password.
     *
     * @param email the email to use for login
     * @param pwd the password to use for login
     * @return true if the login was successful, false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Performs a logout operation.
     */
    public void doLogout() {
        authenticationFacade.doLogout();
    }

    /**
     * Returns the current UserSession.
     *
     * @return the current UserSession
     */
    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * Adds a user role with the specified id and description.
     *
     * @param id the id of the user role
     * @param description the description of the user role
     * @return true if the user role was added successfully, false otherwise
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * Adds a user with the specified name, email, password, and role id.
     *
     * @param name the name of the user
     * @param email the email of the user
     * @param pwd the password of the user
     * @param roleId the role id of the user
     * @return true if the user was added successfully, false otherwise
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }
}