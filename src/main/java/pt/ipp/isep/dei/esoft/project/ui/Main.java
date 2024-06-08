package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

public class Main {

    public static void main(String[] args) {
        try {
            AgendaController agendaController = new AgendaController();
            ToDoListController toDoListController = new ToDoListController();
            AssignVehicleAgendaController assignVehicleAgendaController = new AssignVehicleAgendaController();
            GenerateTeamController generateTeamController = new GenerateTeamController();

            MainMenuUI menu = new MainMenuUI(agendaController, toDoListController,
                    assignVehicleAgendaController, generateTeamController);
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
