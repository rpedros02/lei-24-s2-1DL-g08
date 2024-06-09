package pt.ipp.isep.dei.esoft.project.application.session;

import pt.isep.lei.esoft.auth.domain.model.UserRole;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * This class represents a UserSession in the system.
 * It manages the user's session information and operations.
 */
public class UserSession {

    /**
     * The UserSession object from the authentication library.
     */
    private final pt.isep.lei.esoft.auth.UserSession userSession;

    /**
     * Constructs a UserSession object.
     * It initializes the userSession with the specified UserSession from the authentication library.
     *
     * @param userSession the UserSession from the authentication library
     */
    public UserSession(pt.isep.lei.esoft.auth.UserSession userSession) {
        this.userSession = userSession;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getUserEmail() {
        return userSession.getUserId().getEmail();
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getUserName() {
        return this.userSession.getUserName();
    }

    /**
     * Returns the role of the user.
     * It determines the role based on the user's email.
     *
     * @return the role of the user
     */
    public UserRole getUserRole() {
        String email = this.userSession.getUserId().getEmail();
        if(email.equals("admin@this.app"))
            return new UserRole("admin", "Administrator");
        if(email.equals("gsm@this.app"))
            return new UserRole("gsm", "Green Spaces Manager");
        if(email.equals("hrm@this.app"))
            return new UserRole("hrm", "Human Resources Manager");
        if(email.equals("vfm@this.app"))
            return new UserRole("vfm", "Vehicle Fleet Manager");
        return new UserRole("collaborator", "Collaborator");
    }

    /**
     * Returns the roles of the user.
     *
     * @return the roles of the user
     */
    public List<UserRoleDTO> getUserRoles() {
        return this.userSession.getUserRoles();
    }

    /**
     * Logs out the user.
     */
    public void doLogout() {
        this.userSession.doLogout();
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }

    /**
     * Checks if the user is logged in with a specific role.
     *
     * @param roleId the ID of the role
     * @return true if the user is logged in with the specified role, false otherwise
     */
    public boolean isLoggedInWithRole(String roleId) {
        return this.userSession.isLoggedInWithRole(roleId);
    }
}