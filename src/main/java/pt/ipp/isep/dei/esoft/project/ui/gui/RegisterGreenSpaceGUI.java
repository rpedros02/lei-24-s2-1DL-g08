package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for registering a green space.
 */
public class RegisterGreenSpaceGUI {

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtStreetNumber;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtDistrict;

    @FXML
    private Button btnBack;

    private final AuthenticationRepository authenticationRepository;

    private final GreenSpaceController controller;

    @FXML
    public void initialize() {
        cbType.getItems().addAll(GreenSpaceTypeRepository.getGreenSpaceTypes());
    }

    /**
     * Handles the action of going back to the GSM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the GsmGUI.
     */
    @FXML
    public void handleGsm() {
        handleGSM(btnBack);
    }

    /**
     * Constructs a RegisterGreenSpaceGUI with a GreenSpaceController and an AuthenticationRepository.
     */
    public RegisterGreenSpaceGUI() {
        this.controller = new GreenSpaceController();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

    }

    /**
     * Initializes the user interface.
     * This method is called after all @FXML annotated members have been injected.
     * It sets up the ComboBox with the available green space types.
     */
    @FXML
    public void handleRegisterGreenSpace() {
        String name = txtName.getText();
        String typeName = cbType.getValue();
        String areaString = txtArea.getText();
        String streetNumberString = txtStreetNumber.getText();
        String streetName = txtStreet.getText();
        int streetNumber = Integer.parseInt(streetNumberString);
        String PostalCodeString = txtPostalCode.getText();
        if (!PostalCodeString.matches("[0-9]{4}-[0-9]{3}")) {
            showAlert("Please enter a valid postal code.").showAndWait();
            return;
        }
        String cityString = txtCity.getText();
        String districtString = txtDistrict.getText();

        Address address = new Address(streetName, streetNumber, PostalCodeString, cityString, districtString);

        if (name.isEmpty() || typeName == null || areaString.isEmpty() || streetName.isEmpty() || PostalCodeString.isEmpty() || cityString.isEmpty() || districtString.isEmpty()) {
            showAlert("Please fill in all fields.").showAndWait();
            return;
        }

        double area;
        try {
            area = Double.parseDouble(areaString);
            if (area <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid area.").showAndWait();
            return;
        }

        GreenSpaceTypeRepository type = GreenSpaceTypeRepository.valueOf(typeName.toUpperCase().replace(" ", "_"));
        String email = this.authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Collaborator gsm = organizationRepository.getOrganizationByEmployeeEmail(email).getCollaboratorByEmail(email);

        if (controller.registerGreenSpace(name, type, area, address, gsm)) {
            showSuccess("Green space successfully added.").showAndWait();

            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error adding green space.").showAndWait();
        }
        UtilsGUI.loadUI("/GsmGUI.fxml");
    }

}