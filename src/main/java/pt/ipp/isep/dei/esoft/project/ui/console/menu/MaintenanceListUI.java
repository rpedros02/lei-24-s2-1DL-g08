/*package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;

import java.util.List;

public class MaintenanceListUI implements Runnable {

    private final VehicleCheckupRepository checkupRepository;

    public MaintenanceListUI(VehicleCheckupRepository checkupRepository) {
        this.checkupRepository = checkupRepository;
    }

    @Override
    public void run() {
        List<Vehicle> vehiclesNeedingMaintenance = checkupRepository.getVehiclesNeedingMaintenance();
        if (vehiclesNeedingMaintenance.isEmpty()) {
            System.out.println("No vehicles need maintenance.");
        } else {
            System.out.println("Plate\tBrand\tModel\tCurr.Kms\tFreq\tLast\tNext");
            for (Vehicle vehicle : vehiclesNeedingMaintenance) {
                System.out.printf("%s\t%s\t%s\t%d\t%d\t%d\t%d%n",
                        vehicle.getPlate(), vehicle.getBrand(), vehicle.getModel(),
                        vehicle.getMileage(), vehicle.getMaintenanceFrequency(),
                        vehicle.getLastVehicleCheckupKm(), vehicle.getLastVehicleCheckupKm() + vehicle.getMaintenanceFrequency());
            }
        }
    }
}
*/