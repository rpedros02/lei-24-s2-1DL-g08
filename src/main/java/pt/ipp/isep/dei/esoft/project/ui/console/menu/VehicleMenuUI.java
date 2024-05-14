package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.CreateVehicleUI;

import java.util.ArrayList;
import java.util.List;

public class VehicleMenuUI implements Runnable{
    public VehicleMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Maintenance Menu", new MaintenanceMenuUI()));
        options.add(new MenuItem("Register a new Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Edit a Vehicle", new ShowTextUI("You have chosen Option 3.")));
        options.add(new MenuItem("Maintenance List", new ShowTextUI("You have chosen Option 4.")));
        options.add(new MenuItem("Delete a Vehicle", new ShowTextUI("You have chosen Option 5.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- VEHICLE MENU ------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

}

