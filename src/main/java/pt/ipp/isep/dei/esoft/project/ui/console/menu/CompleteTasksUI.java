package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;

import java.util.Scanner;

public class CompleteTasksUI implements Runnable {
    private final ToDoListController toDoListController;
    public CompleteTasksUI() {
        this.toDoListController = new ToDoListController();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to cancel: ");
        String title = scanner.nextLine();

        boolean result = toDoListController.updateStatus(toDoListController.getToDoListEntry(title), EStatus.CANCELED);
        System.out.println(result ? "Entry cancelled successfully." : "Failed to cancel entry.");


    }
}
