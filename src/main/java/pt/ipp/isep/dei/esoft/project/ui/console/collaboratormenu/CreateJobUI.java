package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Scanner;

/**
 * This class provides the user interface for creating a job.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class CreateJobUI implements Runnable{
    /**
     * The controller that handles the job creation process.
     */
    private final CreateJobController controller;

    /**
     * The name of the job to be created.
     */
    private String jobName;

    /**
     * Constructs a new CreateJobUI.
     * It initializes the controller.
     */
    public CreateJobUI() {
        controller = new CreateJobController();
    }

    /**
     * Returns the controller that handles the job creation process.
     *
     * @return the controller that handles the job creation process
     */
    private CreateJobController getController() {
        return controller;
    }

    /**
     * Starts the job creation process.
     * It first requests the name of the job from the user.
     * It then attempts to create the job with the entered name.
     * If the job creation is successful, it prints a success message.
     * If the job creation is not successful, it prints an error message.
     */
    public void run() {
        System.out.println("\n\n--- Create Task ------------------------");

        requestData();

        submitData();
    }

    /**
     * Attempts to create the job with the entered name.
     * If the job creation is successful, it prints a success message.
     * If the job creation is not successful, it prints an error message.
     */
    private void submitData() {
        if (getController().generateJob(jobName)) {
            System.out.println("\nJob created!");
        } else {
            System.out.println("\nCreation failed!");
        }
    }

    /**
     * Requests the name of the job from the user.
     */
    private void requestData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter job name:");
        jobName = scanner.nextLine();
    }
}
