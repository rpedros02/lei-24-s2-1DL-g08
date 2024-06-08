package pt.ipp.isep.dei.esoft.project.application.session;

import pt.isep.lei.esoft.auth.domain.model.UserRole;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

public class UserSession {

    private final pt.isep.lei.esoft.auth.UserSession userSession;

    public UserSession(pt.isep.lei.esoft.auth.UserSession userSession) {
        this.userSession = userSession;
    }

    public String getUserEmail() {
        return userSession.getUserId().getEmail();
    }

    public String getUserName() {
        return this.userSession.getUserName();
    }

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

    public List<UserRoleDTO> getUserRoles() {
        return this.userSession.getUserRoles();
    }

    public void doLogout() {
        this.userSession.doLogout();
    }

    public boolean isLoggedIn() {
        return this.userSession.isLoggedIn();
    }

    public boolean isLoggedInWithRole(String roleId) {
        return this.userSession.isLoggedInWithRole(roleId);
    }
}