package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;


import java.util.Scanner;

public class AddEntryToAgendaUI {
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;
    private final Scanner scanner;

    public AddEntryToAgendaUI(ToDoListController toDoListController, AgendaController agendaController) {
        this.toDoListController = toDoListController;
        this.agendaController = agendaController;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Enter the title of the entry to add to the agenda:");
        String entryTitle = scanner.nextLine();

        // Verificar se a entrada existe na lista de afazeres
        Entry entry = toDoListController.getToDoListEntry(entryTitle);
        if (entry == null) {
            System.out.println("Entry not found in the To-Do List.");
            return;
        }

        // Verificar se a entrada já está na agenda
        if (agendaController.exists(entryTitle)) {
            System.out.println("Entry already exists in the Agenda.");
            return;
        }

        // Verificar se a entrada está associada a um espaço verde gerenciado pela GSM
        if (entry.getGreenSpace().isManagedByGSM()) {
            System.out.println("Entry is associated with a green space managed by the GSM.");
        } else {
            System.out.println("Entry is not associated with a green space managed by the GSM.");
            return;
        }


        // Adicionar a entrada à agenda
        boolean success = agendaController.addEntry(entry);
        if (success) {
            System.out.println("Entry added to the Agenda successfully.");
        } else {
            System.out.println("Failed to add entry to the Agenda.");
        }
    }
}



