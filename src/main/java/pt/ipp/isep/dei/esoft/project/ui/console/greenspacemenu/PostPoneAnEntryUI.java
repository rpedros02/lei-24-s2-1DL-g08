package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides the user interface for postponing an entry.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class PostPoneAnEntryUI implements Runnable {
    /**
     * The controller that handles the agenda.
     */
    private final AgendaController agendaController;

    /**
     * Constructs a new instance of PostPoneAnEntryUI.
     * It initializes the controller.
     */
    public PostPoneAnEntryUI() {
        this.agendaController = new AgendaController();
    }

    /**
     * Starts the user interface for postponing an entry.
     * It prompts the user to enter the title of the entry to postpone and the new date.
     * It then postpones the entry and prints a success message if the postponement was successful, or an error message otherwise.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to postpone: ");
        String title = scanner.nextLine();

        System.out.print("Enter the new date (dd-MM-yyyy): ");
        String dateStr = scanner.nextLine();

        Date newDate = Utils.dateFromString(dateStr);

        boolean result = agendaController.postponeEntry(title, newDate);
        System.out.println(result ? "Entry postponed successfully." : "Failed to postpone entry.");
    }
}
