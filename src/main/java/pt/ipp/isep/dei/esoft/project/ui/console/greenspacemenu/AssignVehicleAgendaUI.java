package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Scanner;

/**
 * This class provides the user interface for assigning a vehicle to an entry.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AssignVehicleAgendaUI implements Runnable {
    /**
     * The controller that handles the assignment of vehicles to entries.
     */
    private final AssignVehicleAgendaController controller;

    /**
     * The repository that stores the vehicles.
     */
    private final VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();

    /**
     * The scanner to read user input.
     */
    private Scanner input = new Scanner(System.in);

    /**
     * The entry to which a vehicle will be assigned.
     */
    private Entry selectedEntry;

    /**
     * Constructs a new instance of AssignVehicleAgendaUI.
     * It initializes the controller.
     */
    public AssignVehicleAgendaUI() {
        this.controller = new AssignVehicleAgendaController();
    }

    /**
     * Starts the user interface for assigning a vehicle to an entry.
     * It first selects an entry and a vehicle.
     * It then assigns the selected vehicle to the selected entry.
     * It continues to assign vehicles to the selected entry until the user decides to stop.
     */
    @Override
    public void run() {
        System.out.println("Welcome to the assignment of a vehicle to an entry");
        selectedEntry = SelectEntry();
        boolean continueAssigning = true;
        while (continueAssigning) {
            Vehicle selectedVehicle = SelectVehicles();
            controller.assignVehicle(selectedEntry, selectedVehicle);
            displayEntry();
            continueAssigning = continueAssigning();
        }
        System.out.println("Assignment complete!");
    }

    /**
     * Selects a vehicle from the list of vehicles.
     * It prompts the user to select a vehicle.
     *
     * @return the selected vehicle
     */
    private Vehicle SelectVehicles() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();

        int numberOfVehicle = vehicles.size();
        int answer = -1;

        while (answer < 1 || answer > numberOfVehicle) {
            displayVehicles();
            System.out.println("Select a vehicle: ");
            answer = input.nextInt();
        }

        Vehicle vehicle = vehicles.get(answer - 1);
        return vehicle;
    }

    /**
     * Displays the list of vehicles.
     */
    private void displayVehicles() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        int i = 1;
        for (Vehicle vehicle : vehicles) {
            System.out.println("  " + i + " - " + vehicle.toString());
            i++;
        }
    }

    /**
     * Selects an entry from the list of entries.
     * It prompts the user to select an entry.
     *
     * @return the selected entry
     */
    private Entry SelectEntry() {
        List<Entry> entries = controller.getAgenda().getEntries();

        int numberOfEntry = entries.size();
        int answer = -1;

        while (answer < 1 || answer > numberOfEntry) {
            displayEntry();
            System.out.println("Select entry: ");
            answer = input.nextInt();
        }

        Entry entry = entries.get(answer - 1);
        return entry;
    }

    /**
     * Displays the list of entries.
     */
    private void displayEntry() {
        List<Entry> entries = controller.getAgenda().getEntries();
        int i = 1;
        for (Entry entry : entries) {
            System.out.println("  " + i + " - " + entry.toString());
            i++;
        }
    }

    /**
     * Asks the user if they want to continue assigning vehicles to the selected entry.
     *
     * @return true if the user wants to continue assigning vehicles, false otherwise
     */
    private boolean continueAssigning() {
        System.out.println("Do you want to assign another vehicle? (yes/no): ");
        String response = input.next().trim().toLowerCase();
        return response.equals("yes");
    }
}
