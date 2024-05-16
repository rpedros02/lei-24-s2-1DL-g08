package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.ArrayList;
import java.util.List;
public class GreenSpaceController {
    private GreenSpaceRepository repository;

    public GreenSpaceController(GreenSpaceRepository repository) {
        this.repository = repository;
    }

    public void registerGreenSpace(String name, int locationCoordinates, double area) {
        GreenSpace greenSpace = new GreenSpace(name, locationCoordinates, area);
        repository.addGreenSpace(greenSpace);
    }

    public List<GreenSpace> getAllGreenSpaces() {
        return repository.getAllGreenSpaces();
    }
}
