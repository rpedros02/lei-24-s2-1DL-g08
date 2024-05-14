package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CreateVehicleCheckupUI;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceMenuUI implements Runnable{
    public MaintenanceMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Check-Up", new CreateVehicleCheckupUI()));
        options.add(new MenuItem("Maintenance List", new ShowTextUI("You have chosen Option 2.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Maintenance MENU ------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

}