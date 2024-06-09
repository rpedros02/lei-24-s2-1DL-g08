package pt.ipp.isep.dei.esoft.project.ui.console.authorization;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * This class provides the user interface for the authentication process.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AuthenticationUI implements Runnable {
    /**
     * The controller that handles the authentication process.
     */
    private final AuthenticationController ctrl;

    /**
     * Constructs a new AuthenticationUI.
     * It initializes the controller.
     */
    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    /**
     * Starts the authentication process.
     * It first attempts to log in the user.
     * If the login is successful, it retrieves the user's roles and allows the user to select a role.
     * It then redirects the user to the appropriate user interface based on the selected role.
     * If the login is not successful or no role is selected, it logs out the user.
     */
    public void run() {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    /**
     * Returns a list of menu items for the user roles.
     * Each menu item represents a user role and its corresponding user interface.
     *
     * @return a list of menu items for the user roles
     */
    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HrmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VfmUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_GSM, new GsmUI()));

        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    /**
     * Attempts to log in the user.
     * It prompts the user to enter their user id and password.
     * It then attempts to log in the user with the entered user id and password.
     * If the login is not successful, it allows the user to try again up to a maximum number of attempts.
     *
     * @return true if the login is successful, false otherwise
     */
    private boolean doLogin() {
        System.out.println("\n\n--- LOGIN UI ---------------------------");

        int maxAttempts = 3;
        boolean success;
        do {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            String pwd = Utils.readLineFromConsole("Enter Password: ");

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    /**
     * Logs out the user.
     */
    private void logout() {
        ctrl.doLogout();
    }

    /**
     * Redirects the user to the appropriate user interface based on the selected role.
     * If there is no user interface for the selected role, it prints a message indicating this.
     *
     * @param rolesUI the list of menu items for the user roles
     * @param role the selected role
     */
    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    /**
     * Allows the user to select a role from a list of roles.
     * If there is only one role in the list, it automatically selects that role.
     * If there are multiple roles in the list, it prompts the user to select a role.
     *
     * @param roles the list of roles
     * @return the selected role
     */
    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.getFirst();
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }
}
