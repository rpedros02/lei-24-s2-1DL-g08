package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

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

        String taskTitle = Utils.readLineFromConsole("Enter the task title:");
        String taskDescription = Utils.readLineFromConsole("Enter the task description:");
        List<String> types = DegreeOfUrgencyRepository.getDegreesOfUrgency();
        String degreeOfUrgencyString = (String) Utils.showAndSelectOne(types, "Select a degree of urgency:");
        double taskDuration = Utils.readDoubleFromConsole("Enter the task duration:");
        String taskStatus = "Pending"; // Assuming the task status is "Pending" when it's created

        DegreeOfUrgencyRepository degreeOfUrgency = DegreeOfUrgencyRepository.valueOf(degreeOfUrgencyString.toUpperCase());
        Task task = new Task(taskTitle, taskDescription, degreeOfUrgency, taskDuration, taskStatus, greenSpace);

        if (toDoListController.addTaskToToDoList(task)) {
            System.out.println("Task successfully added to the To-Do List.");
        } else {
            System.out.println("Failed to add task to the To-Do List.");
        }
    }
}
