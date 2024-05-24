package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.repository.DegreeOfUrgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;

import java.util.List;

public class AddEntryToToDoListUI implements Runnable {
    private GreenSpaceController greenSpaceController;
    private ToDoListController toDoListController;

    /**
     * Instantiates a new Add Entry to To-Do List UI.
     */
    public AddEntryToToDoListUI() {
        this.greenSpaceController = new GreenSpaceController();
        this.toDoListController = new ToDoListController();
    }

    /**
     * Runs the Add Entry to To-Do List UI.
     */
    @Override
    public void run() {
        List<GreenSpace> greenSpaces = greenSpaceController.getAllGreenSpaces();
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }
        int greenSpaceIndex = Utils.readIntegerFromConsole("Select a green space from the list above:") - 1;
        GreenSpace greenSpace = greenSpaces.get(greenSpaceIndex);

        String entryTitle = Utils.readLineFromConsole("Enter the entry title:");
        String entryDescription = Utils.readLineFromConsole("Enter the entry description:");
        List<String> types = DegreeOfUrgencyRepository.getDegreesOfUrgency();
        String degreeOfUrgencyString = (String) Utils.showAndSelectOne(types, "Select a degree of urgency:");
        double entryDuration = Utils.readDoubleFromConsole("Enter the entry duration:");
        String entryStatus = "Pending"; // Assuming the entry status is "Pending" when it's created

        DegreeOfUrgencyRepository degreeOfUrgency = DegreeOfUrgencyRepository.valueOf(degreeOfUrgencyString.toUpperCase());
        Entry entry = new Entry(entryTitle, entryDescription, degreeOfUrgency, entryDuration, entryStatus, greenSpace);

        if (toDoListController.addEntryToToDoList(entry)) {
            System.out.println("Entry successfully added to the To-Do List.");
        } else {
            System.out.println("Failed to add entry to the To-Do List.");
        }
    }
}
