package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu;

import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddTaskToEntryUI implements Runnable{

    private final OrganizationRepository organizationRepository;
    private final ToDoListController toDoListController;

    public AddTaskToEntryUI(Entry entry) {
        this.organizationRepository = Repositories.getInstance().getOrganizationRepository();
        toDoListController = new ToDoListController();
    }

    @Override
    public void run() {
        String email = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
        Organization organization = organizationRepository.getOrganizationByEmployeeEmail(email);
        ToDoList toDoList = organization.getTodoList();
        Agenda agenda = organization.getAgenda();
        System.out.println("-- Add Task to Entry: --");
        List<Task> availableTasks = new ArrayList<>(organization.getTasks());
        Utils.showAndSelectOne(availableTasks, "Select a Task to add to the Entry:");
    }
}
