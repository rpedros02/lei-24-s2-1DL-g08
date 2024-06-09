package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class provides the user interface for creating a task.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CreateTaskUI implements Runnable {

    // Controller for task creation
    private final CreateTaskController controller;
    // Task attributes
    private String taskReference;
    private String taskDescription;
    private String taskInformalDescription;
    private String taskTechnicalDescription;
    private int taskDuration;
    private double taskCost;
    private String taskCategoryDescription;
    private String employeeEmail;

    /**
     * Constructs a new instance of CreateTaskUI.
     */
    public CreateTaskUI() {
        controller = new CreateTaskController();
    }

    /**
     * Returns the task controller.
     * @return the task controller
     */
    private CreateTaskController getController() {
        return controller;
    }

    /**
     * Starts the user interface for creating a task.
     * It prompts the user to input the task data and submits the data to create a task.
     */
    public void run() {
        System.out.println("\n\n--- Create Task ------------------------");

        taskCategoryDescription = displayAndSelectTaskCategory();

        requestData();

        submitData();
    }

    /**
     * Submits the task data to create a task.
     * If the task is created successfully, it displays a success message.
     * Otherwise, it displays a failure message.
     */
    private void submitData() {
        Optional<Task> task = getController().createTask(taskReference, taskDescription, taskInformalDescription,
                taskTechnicalDescription, taskDuration, taskCost, taskCategoryDescription);

        if (task.isPresent()) {
            System.out.println("\nTask successfully created!");
        } else {
            System.out.println("\nTask not created!");
        }
    }

    /**
     * Requests the user to input the task data.
     */
    private void requestData() {

        //Request the Task Reference from the console
        taskReference = requestTaskReference();

        //Request the Task Description from the console
        taskDescription = requestTaskDescription();

        //Request the Task Informal Description from the console
        taskInformalDescription = requestTaskInformalDescription();

        //Request the Task Technical Description from the console
        taskTechnicalDescription = requestTaskTechnicalDescription();

        //Request the Task Duration from the console
        taskDuration = requestTaskDuration();

        //Request the Task Cost from the console
        taskCost = requestTaskCost();
    }

    /**
     * Requests the user to input the task cost.
     * @return the input task cost
     */
    private double requestTaskCost() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Cost: ");
        return input.nextDouble();
    }

    /**
     * Requests the user to input the task duration.
     * @return the input task duration
     */
    private int requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Duration: ");
        return input.nextInt();
    }

    /**
     * Requests the user to input the task technical description.
     * @return the input task technical description
     */
    private String requestTaskTechnicalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Technical Description: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the task informal description.
     * @return the input task informal description
     */
    private String requestTaskInformalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Informal Description: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the task description.
     * @return the input task description
     */
    private String requestTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Description: ");
        return input.nextLine();
    }

    /**
     * Requests the user to input the task reference.
     * @return the input task reference
     */
    private String requestTaskReference() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Reference: ");
        return input.nextLine();
    }

    /**
     * Displays the list of task categories and prompts the user to select one.
     * @return the description of the selected task category
     */
    private String displayAndSelectTaskCategory() {
        //Display the list of task categories
        List<TaskCategory> taskCategories = controller.getTaskCategories();

        int listSize = taskCategories.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayTaskCategoryOptions(taskCategories);
            System.out.print("Select a task category: ");
            answer = input.nextInt();
        }

        String description = taskCategories.get(answer - 1).getDescription();
        return description;
    }

    /**
     * Displays the list of task categories as a menu with number options to select.
     * @param taskCategories the list of task categories
     */
    private void displayTaskCategoryOptions(List<TaskCategory> taskCategories) {
        int i = 1;
        for (TaskCategory taskCategory : taskCategories) {
            System.out.println("  " + i + " - " + taskCategory.getDescription());
            i++;
        }
    }

    /**
     * This class provides the user interface for creating a job.
     */
    public static class CreateJobUI {
    }
}
