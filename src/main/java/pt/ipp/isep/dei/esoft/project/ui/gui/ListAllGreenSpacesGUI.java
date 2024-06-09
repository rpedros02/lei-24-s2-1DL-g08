package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

public class ListAllGreenSpacesGUI {
    @FXML
    private ListView<GreenSpace> agendaListView;

    @FXML
    private Button btnReturn;

    private GreenSpaceRepository greenSpaceRepository;


    public ListAllGreenSpacesGUI() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    public List<GreenSpace> getAllGreenSpaces() {
        return this.greenSpaceRepository.getGreenSpaces();
    }

    @FXML
    public void handleReturn() {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        loadUI("/GsmGUI.fxml");
        stage.close();
    }

    @FXML
    private void handleListAllGreenSpaces() {
        List<GreenSpace> greenSpaces = getAllGreenSpaces();
        agendaListView.getItems().setAll(greenSpaces);
    }
}
