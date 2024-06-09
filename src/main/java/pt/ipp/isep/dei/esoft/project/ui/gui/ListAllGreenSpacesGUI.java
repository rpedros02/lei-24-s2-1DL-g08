package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.handleGSM;

/**
 * This class provides a user interface for listing all green spaces.
 */
public class ListAllGreenSpacesGUI {
    @FXML
    // ListView for displaying the green spaces
    private ListView<GreenSpace> agendaListView;

    @FXML
    // Button for returning to the previous user interface
    private Button btnReturn;

    // Repository for accessing green spaces
    private final GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructs a ListAllGreenSpacesGUI with a GreenSpaceRepository.
     */
    public ListAllGreenSpacesGUI() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Retrieves all green spaces from the repository.
     * @return a list of all green spaces
     */
    public List<GreenSpace> getAllGreenSpaces() {
        return this.greenSpaceRepository.getGreenSpaces();
    }

    /**
     * Handles the action of returning to the previous user interface.
     * It is triggered when the Return button is clicked.
     * It closes the current stage and loads the GsmGUI.
     */
    @FXML
    public void handleReturn() {
        handleGSM(btnReturn);
    }

    /**
     * Handles the action of listing all green spaces.
     * It is triggered when the corresponding button is clicked.
     * It retrieves all green spaces from the repository and displays them in the ListView.
     */
    @FXML
    private void handleListAllGreenSpaces() {
        List<GreenSpace> greenSpaces = getAllGreenSpaces();
        agendaListView.getItems().setAll(greenSpaces);
    }
}
