package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.util.List;

public class GreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;
    public GreenSpaceController() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }
    public boolean registerGreenSpace(String name, GreenSpaceTypeRepository type, double area, Address address) {
        GreenSpace greenSpace = new GreenSpace(name, type, area, address);
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
    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area, Address address) {
        return greenSpaceRepository.updateGreenSpace(greenSpace, name, type, area, address);
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