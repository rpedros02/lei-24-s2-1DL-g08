package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

public class RegisterGreenSpaceUI implements Runnable {
    private GreenSpaceController controller;

    /**
     * Creates an instance of RegisterGreenSpaceUI.
     */
    public RegisterGreenSpaceUI() {
        this.controller = new GreenSpaceController();
    }

    /**
     * Starts the Register Green Space UI.
     */
    @Override
    public void run() {
        String name = Utils.readLineFromConsole("Type the green space name:");
        List<String> types = GreenSpaceTypeRepository.getGreenSpaceTypes();
        String typeName = (String) Utils.showAndSelectOne(types, "Select a green space type:");
        double area = Utils.readDoubleFromConsole("Type the green space area:");

        GreenSpaceTypeRepository type = GreenSpaceTypeRepository.valueOf(typeName.toUpperCase().replace(" ", "_"));
        if (controller.registerGreenSpace(name, type, area)) {
            System.out.println("Green space successfully added.");
        } else {
            System.out.println("Failed to add green space. Green space already exists.");
        }
    }
}
