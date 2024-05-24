package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.ArrayList;
import java.util.List;
public class GreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;
    public GreenSpaceController() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
    }
    public boolean registerGreenSpace(String name, GreenSpaceTypeRepository type, double area) {
        GreenSpace greenSpace = new GreenSpace(name, type, area);
        return greenSpaceRepository.addGreenSpace(greenSpace);
    }
    public List<GreenSpace> getAllGreenSpaces() {
        return greenSpaceRepository.getGreenSpaces();
    }
    public GreenSpace getGreenSpaceByName(String name) {
        return greenSpaceRepository.getGreenSpaceByName(name);
    }
    public boolean removeGreenSpace(GreenSpace greenSpace) {
        return greenSpaceRepository.removeGreenSpace(greenSpace);
    }
    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area) {
        return greenSpaceRepository.updateGreenSpace(greenSpace, name, type, area);
    }
    public void setGreenSpaceRepository(GreenSpaceRepository greenSpaceRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
    }
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }
    public void setGreenSpaceRepository() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }
}