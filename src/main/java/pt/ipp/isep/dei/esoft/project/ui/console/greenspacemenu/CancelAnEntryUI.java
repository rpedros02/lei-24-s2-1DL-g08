package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;

import java.util.Scanner;

public class CancelAnEntryUI implements Runnable {
    private final AgendaController agendaController;

    public CancelAnEntryUI(AgendaController agendaController) {
        this.agendaController = agendaController;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to cancel: ");
        String title = scanner.nextLine();

        boolean result = agendaController.cancelEntry(title);
        System.out.println(result ? "Entry cancelled successfully." : "Failed to cancel entry.");
    }
}
