package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

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
        Address address = requestAddress();

        GreenSpaceTypeRepository type = GreenSpaceTypeRepository.valueOf(typeName.toUpperCase().replace(" ", "_"));
        if (controller.registerGreenSpace(name, type, area, address)) {
            System.out.println("Green space successfully added.");
        } else {
            System.out.println("Failed to add green space. Green space already exists.");
        }
    }
    private Address requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Address--\n");
        System.out.println("Street: ");
        String street = input.nextLine();
        System.out.println("\nDoor Number: ");
        int doorNumber = input.nextInt();
        input.nextLine();
        System.out.println("\nPostal Code: ");
        String postalCode = input.nextLine();
        System.out.println("\nCity: ");
        String city = input.nextLine();
        System.out.println("\nDistrict: ");
        String district = input.nextLine();
        return new Address(street, doorNumber, postalCode, city, district);
    }
}
