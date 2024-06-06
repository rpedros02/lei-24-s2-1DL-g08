package pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu;


import pt.ipp.isep.dei.esoft.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.VehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;


import java.util.List;

public class CheckUpsUI implements Runnable {
    private final ListGreenSpacesController controller;

    public CheckUpsUI() {
        this.controller = new ListGreenSpacesController();
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