package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.CreateVehicleUI;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CheckUpsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.vehiclemenu.maintenancemenu.CreateVehicleCheckupUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VfmUI implements Runnable {
    public VfmUI() {
    }

    /**
     * Runs the VFM UI.
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create Vehicle", new CreateVehicleUI()));
        options.add(new MenuItem("Register a Vehicle Check up", new CreateVehicleCheckupUI()));
        options.add(new MenuItem("Register Check Up or See Vehicles needing Check Up", new CheckUpsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- VFM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
