/*
package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleCheckupRepository;

import java.util.List;

public class MaintenanceReportController {

    private final VehicleCheckupRepository checkupRepository;

    public MaintenanceReportController(VehicleCheckupRepository checkupRepository) {
        this.checkupRepository = checkupRepository;
    }


     * Gera e retorna um relatório de manutenção com os dados dos veículos que necessitam de manutenção.
     *
     * @param currentKms Kilômetros atuais dos veículos
     * @return O relatório de manutenção

    public String generateMaintenanceReport(int currentKms) {
        List<Vehicle> vehiclesNeedingMaintenance = checkupRepository.getVehiclesNeedingMaintenance(currentKms);
        StringBuilder report = new StringBuilder();
        report.append("Plate\tBrand\tModel\tCurr.Kms\tFreq\tLast\tNext\n");
        for (Vehicle vehicle : vehiclesNeedingMaintenance) {
            report.append(vehicle.getPlateId()).append("\t")
                    .append(vehicle.getBrand()).append("\t")
                    .append(vehicle.getModel()).append("\t")
                    .append(vehicle.getMileage()).append("\t")
                    .append(vehicle.getMaintenanceFrequency()).append("\t")
                    .append(vehicle.getLastVehicleCheckupKm()).append("\t")
                    .append(vehicle.getLastVehicleCheckupKm() + vehicle.getMaintenanceFrequency()).append("\n");
        }
        return report.toString();
    }
}

*/