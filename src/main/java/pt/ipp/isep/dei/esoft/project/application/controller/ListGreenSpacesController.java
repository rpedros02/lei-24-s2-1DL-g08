package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListGreenSpacesController {
    private GreenSpaceRepository greenSpaceRepository;

    public ListGreenSpacesController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }
    public List<GreenSpace> getAllGreenSpaces(){
        return this.greenSpaceRepository.getGreenSpaces();
    }

}