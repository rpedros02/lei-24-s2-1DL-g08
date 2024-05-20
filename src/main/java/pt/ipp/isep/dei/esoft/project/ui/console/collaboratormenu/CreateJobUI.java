package pt.ipp.isep.dei.esoft.project.ui.console.collaboratormenu;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Scanner;

public class CreateJobUI implements Runnable{
    private final CreateJobController controller;
    private String jobName;

    public CreateJobUI() {

        controller = new CreateJobController();
    }

    private CreateJobController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Create Task ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {

       if (getController().generateJob(jobName)) {
            System.out.println("\nJob created!");
        } else {
            System.out.println("\nCreation failed!");
        }
    }

    private void requestData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter job name:");
        jobName = scanner.nextLine();
    }

}
