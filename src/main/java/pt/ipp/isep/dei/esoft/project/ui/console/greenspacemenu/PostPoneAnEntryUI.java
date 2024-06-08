package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date newDate = null;
        try {
            newDate = new Date(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        boolean result = agendaController.postponeEntry(title, newDate);
        System.out.println(result ? "Entry postponed successfully." : "Failed to postpone entry.");
    }
}
