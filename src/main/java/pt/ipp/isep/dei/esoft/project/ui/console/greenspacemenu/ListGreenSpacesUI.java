package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import java.util.List;

public class ListGreenSpacesUI implements Runnable {
    private final ListGreenSpacesController controller;
    private final AuthenticationController authController;

    public ListGreenSpacesUI() {
        this.controller = new ListGreenSpacesController();
        this.authController = new AuthenticationController();
    }

    @Override
    public void run() {
        System.out.println("Green Spaces:");
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        displayGreenSpaces(greenSpaces);
        System.out.println("--------------------");
    }

    private void displayGreenSpaces(List<GreenSpace> greenSpaces) {
        if (greenSpaces.isEmpty()) {
            System.out.println("No green spaces found for this GSM.");
        } else {
            for (GreenSpace greenSpace : greenSpaces) {
                System.out.println(greenSpace);
            }
        }
    }
}