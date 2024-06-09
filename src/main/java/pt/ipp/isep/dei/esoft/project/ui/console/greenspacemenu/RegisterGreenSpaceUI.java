package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * This class provides the user interface for registering a Green Space.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class RegisterGreenSpaceUI implements Runnable {
    /**
     * The controller that handles the registration of Green Spaces.
     */
    private GreenSpaceController controller;

    /**
     * Constructs a new instance of RegisterGreenSpaceUI.
     * It initializes the controller.
     */
    public RegisterGreenSpaceUI() {
        this.controller = new GreenSpaceController();
    }

    /**
     * Starts the user interface for registering a Green Space.
     * It prompts the user to enter the name, type, area, and address of the Green Space, and the email of the GSM.
     * It then registers the Green Space and prints a success message if the registration was successful, or an error message otherwise.
     */
    @Override
    public void run() {
        String name = Utils.readLineFromConsole("Type the green space name:");
        List<String> types = GreenSpaceTypeRepository.getGreenSpaceTypes();
        String typeName = (String) Utils.showAndSelectOne(types, "Select a green space type:");
        double area = Utils.readDoubleFromConsole("Type the green space area:");
        Address address = Utils.requestAddress();
        Collaborator gsm = requestCollaborator();

        GreenSpaceTypeRepository type = GreenSpaceTypeRepository.valueOf(typeName.toUpperCase().replace(" ", "_"));
        if (controller.registerGreenSpace(name, type, area, address, gsm)) {
            System.out.println("Green space successfully added.");
        } else {
            System.out.println("Failed to add green space. Green space already exists.");
        }
    }

    /**
     * Requests the GSM collaborator by prompting the user to enter the GSM email.
     * It then retrieves the collaborator with the entered email from the organization repository.
     *
     * @return the collaborator with the entered email
     */
    private Collaborator requestCollaborator() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type the GSM email:");
        String email = input.nextLine();
        return OrganizationRepository.getInstance().getOrganizationByEmployeeEmail(email).getCollaboratorByEmail(email);
    }
}