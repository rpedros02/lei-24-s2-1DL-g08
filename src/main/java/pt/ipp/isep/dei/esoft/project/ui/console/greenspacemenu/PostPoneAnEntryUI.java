package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PostPoneAnEntryUI implements Runnable {
    private final PostponeAnEntryController postponeAnEntryController;
    private final Agenda agenda;

    public PostPoneAnEntryUI(Agenda agenda) {
        this.agenda = agenda;
        this.postponeAnEntryController = new PostponeAnEntryController(agenda);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to postpone: ");
        String title = scanner.nextLine();

        System.out.print("Enter the new date (dd-MM-YYYY): ");
        String dateStr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        Date newDate = new Date(dateStr);
        String result = postponeAnEntryController.postponeEntry(title, newDate);
        System.out.println(result);
    }
}
