package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import java.util.List;

/**
 * The AssignVehicleAgendaController class is responsible for handling operations related to assigning vehicles to the agenda.
 * It interacts with the Agenda and VehicleRepository to perform operations such as retrieving the agenda, retrieving the vehicle repository, assigning vehicles to the agenda, and retrieving a list of vehicles.
 */
public class AssignVehicleAgendaController {

    /**
     * The Agenda instance.
     * This instance is used to interact with the agenda,
     * allowing the controller to perform operations related to the agenda.
     */
    private Agenda agenda;

    /**
     * The VehicleRepository instance.
     * This instance is used to interact with the vehicle repository,
     * allowing the controller to perform operations related to vehicles.
     */
    private VehicleRepository vehicleRepository;

    /**
     * The constructor for the AssignVehicleAgendaController.
     * It initializes the Agenda and VehicleRepository instances.
     */
    public AssignVehicleAgendaController(){
        getAgenda();
        getVehicleRepository();
    }

    /**
     * Retrieves the agenda.
     * If the agenda is null, it retrieves the agenda from the repositories.
     *
     * @return The Agenda object.
     */
    public Agenda getAgenda(){
        if(agenda == null){
            Repositories repositories = Repositories.getInstance();

            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    /**
     * Retrieves the vehicle repository.
     * If the vehicle repository is null, it retrieves the vehicle repository from the repositories.
     *
     * @return The VehicleRepository object.
     */
    public VehicleRepository getVehicleRepository(){
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Assigns a vehicle to the agenda.
     *
     * @param agenda The Entry object representing the agenda to which the vehicle is to be assigned.
     * @param vehicle The Vehicle object to be assigned.
     */
    public void assignVehicle(Entry agenda, Vehicle vehicle) {
        agenda.addVehicle(vehicle);
    }

    /**
     * Retrieves a list of vehicles.
     *
     * @return A list of Vehicle objects.
     */
    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }
}