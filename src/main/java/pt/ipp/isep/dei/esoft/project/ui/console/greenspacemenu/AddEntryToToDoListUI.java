package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;

import java.util.List;

public class AddEntryToToDoListUI implements Runnable {
    private final GreenSpaceController greenSpaceController;
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;

    public AddEntryToToDoListUI(ToDoListController toDoListController, AgendaController agendaController) {
        this.greenSpaceController = new GreenSpaceController();
        this.toDoListController = toDoListController;
        this.agendaController = agendaController;
    }

    @Override
    public void run() {
        System.out.println("-- Add Entry to To-Do List: --");
        GreenSpace greenSpace = getGreenSpace();
        String entryTitle = getEntryName();
        String entryDescription = getEntryDescription();
        List<String> types = DegreeOfUrgency.getDegreesOfUrgency();
        String degreeOfUrgencyString = (String) Utils.showAndSelectOne(types, "Select a degree of urgency:");
        Date dateBegin = Utils.readDateFromConsole("Enter the entry begin date (dd-MM-yyyy):");
        Date dateEnd = Utils.readDateFromConsole("Enter the entry end date (dd-MM-yyyy):");

        EStatus entryStatus = EStatus.PENDING;

        DegreeOfUrgency degreeOfUrgency = DegreeOfUrgency.valueOf(degreeOfUrgencyString.toUpperCase());
        Entry entry = new Entry(entryTitle, entryDescription, degreeOfUrgency, dateBegin, dateEnd, entryStatus, greenSpace);

        boolean flag = Utils.getBooleanAnswer("Do you want to link a Task to this Entry?");

        if (flag) {
            AddTaskToEntryUI taskUI = new AddTaskToEntryUI(entry);
            taskUI.run();
        }

        if (toDoListController.addEntry(entry) && agendaController.addEntry(entry)) {
            System.out.println("Entry successfully added to the To-Do List and registered in the Agenda.");
        } else {
            System.out.println("Failed to create Entry.");
        }
    }

    private GreenSpace getGreenSpace() {
        List<GreenSpace> greenSpaces = greenSpaceController.getAllGreenSpaces();
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }
        int greenSpaceIndex = Utils.readIntegerFromConsole("Select a green space from the list above:") - 1;
        return greenSpaces.get(greenSpaceIndex);
    }

    private String getEntryName() {
        return Utils.readLineFromConsole("Enter the entry title:");
    }

    private String getEntryDescription() {
        return Utils.readLineFromConsole("Enter the entry description:");
    }
}
