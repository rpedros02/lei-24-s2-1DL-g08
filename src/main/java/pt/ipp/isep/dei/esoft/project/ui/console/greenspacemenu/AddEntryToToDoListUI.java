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

/**
 * This class provides the user interface for adding an entry to the To-Do List.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class AddEntryToToDoListUI implements Runnable {
    /**
     * The controller that handles the Green Space.
     */
    private final GreenSpaceController greenSpaceController;

    /**
     * The controller that handles the To-Do List.
     */
    private final ToDoListController toDoListController;

    /**
     * The controller that handles the agenda.
     */
    private final AgendaController agendaController;

    /**
     * Constructs a new instance of AddEntryToToDoListUI.
     * It initializes the controllers.
     */
    public AddEntryToToDoListUI() {
        this.greenSpaceController = new GreenSpaceController();
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
    }

    /**
     * Starts the process of adding an entry to the To-Do List.
     * It prompts the user to enter the details of the entry.
     * It then creates the entry and adds it to the To-Do List and the agenda.
     * If the user wants to link a task to the entry, it starts the process of adding a task to the entry.
     * If the entry is successfully added to the To-Do List and the agenda, it prints a success message.
     * If the entry is not successfully added to the To-Do List and the agenda, it prints an error message.
     */

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

        EStatus entryStatus = EStatus.PLANNED;

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

    /**
     * Returns the selected Green Space.
     * It prompts the user to select a Green Space from a list of Green Spaces.
     *
     * @return the selected Green Space
     */
    private GreenSpace getGreenSpace() {
        List<GreenSpace> greenSpaces = greenSpaceController.getAllGreenSpaces();
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }
        int greenSpaceIndex = Utils.readIntegerFromConsole("Select a green space from the list above:") - 1;
        return greenSpaces.get(greenSpaceIndex);
    }

    /**
     * Returns the title of the entry.
     * It prompts the user to enter the title of the entry.
     *
     * @return the title of the entry
     */
    private String getEntryName() {
        return Utils.readLineFromConsole("Enter the entry title:");
    }

    /**
     * Returns the description of the entry.
     * It prompts the user to enter the description of the entry.
     *
     * @return the description of the entry
     */
    private String getEntryDescription() {
        return Utils.readLineFromConsole("Enter the entry description:");
    }
}
