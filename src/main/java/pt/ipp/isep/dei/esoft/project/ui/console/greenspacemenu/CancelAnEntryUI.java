package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;

import java.util.Scanner;

/**
 * This class provides the user interface for cancelling an entry.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CancelAnEntryUI implements Runnable {
    /**
     * The controller that handles the agenda.
     */
    private final AgendaController agendaController;

    /**
     * Constructs a new instance of CancelAnEntryUI.
     * It initializes the controller.
     */
    public CancelAnEntryUI() {
        this.agendaController = new AgendaController();
    }

    /**
     * Starts the user interface for cancelling an entry.
     * It prompts the user to enter the title of the entry to cancel.
     * It then cancels the entry and prints a success message if the cancellation was successful, or an error message otherwise.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to cancel: ");
        String title = scanner.nextLine();

        boolean result = agendaController.cancelEntry(title);
        System.out.println(result ? "Entry cancelled successfully." : "Failed to cancel entry.");
    }
}
