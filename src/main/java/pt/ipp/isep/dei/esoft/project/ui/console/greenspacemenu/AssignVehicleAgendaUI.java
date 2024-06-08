package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehicleAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.List;
import java.util.Scanner;

public class AssignVehicleAgendaUI implements Runnable {
    private final AssignVehicleAgendaController controller;
    private final VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    private Scanner input = new Scanner(System.in);
    private Entry selectedEntry;

    public AssignVehicleAgendaUI(AssignVehicleAgendaController controller) {
        this.controller = controller;
    }

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

    private void displayVehicles() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        int i = 1;
        for (Vehicle vehicle : vehicles) {
            System.out.println("  " + i + " - " + vehicle.toString());
            i++;
        }
    }

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

    private void displayEntry() {
        List<Entry> entries = controller.getAgenda().getEntries();
        int i = 1;
        for (Entry entry : entries) {
            System.out.println("  " + i + " - " + entry.toString());
            i++;
        }
    }

    private boolean continueAssigning() {
        System.out.println("Do you want to assign another vehicle? (yes/no): ");
        String response = input.next().trim().toLowerCase();
        return response.equals("yes");
    }
}
