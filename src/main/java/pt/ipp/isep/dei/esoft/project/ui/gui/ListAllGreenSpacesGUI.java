package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListAllGreenSpacesGUI {
    private GreenSpaceRepository greenSpaceRepository;

    public ListAllGreenSpacesGUI() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    public List<GreenSpace> getAllGreenSpaces() {
        return this.greenSpaceRepository.getGreenSpaces();
    }

    @FXML
    private ListView<GreenSpace> agendaListView;

    @FXML
    private void handleListAllGreenSpaces() {
        List<GreenSpace> greenSpaces = getAllGreenSpaces();
        agendaListView.getItems().setAll(greenSpaces);
    }
}
