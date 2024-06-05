package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.CancelAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;

import java.util.Scanner;

public class CancelAnEntryUI implements Runnable {
    private final CancelAnEntryController cancelAnEntryController;

    public CancelAnEntryUI(Agenda agenda) {
        this.cancelAnEntryController = new CancelAnEntryController(agenda);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to cancel: ");
        String title = scanner.nextLine();

        String result = cancelAnEntryController.cancelEntry(title);
        System.out.println(result);
    }
}
