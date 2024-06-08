package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PostPoneAnEntryUI implements Runnable {
    private final AgendaController agendaController;

    public PostPoneAnEntryUI() {
        this.agendaController = new AgendaController();
    }

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
