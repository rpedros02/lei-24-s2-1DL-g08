package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;


import java.util.List;

public class AssignVehicleAgendaController {

    private Agenda agenda;

    private VehicleRepository vehicleRepository;

    public AssignVehicleAgendaController(){
        getAgenda();
        getVehicleRepository();
    }



    public Agenda getAgenda(){
        if(agenda == null){
            Repositories repositories = Repositories.getInstance();

            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    public VehicleRepository getVehicleRepository(){
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    public void assignVehicle(Entry agenda, Vehicle vehicle) {
        agenda.addVehicle(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }


}