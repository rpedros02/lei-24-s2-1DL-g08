package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.loadUI;

/**
 * This class provides a user interface for registering a green space.
 */
public class RegisterGreenSpaceGUI {

    @FXML
    // TextField for entering the green space's name
    private TextField txtName;

    @FXML
    // ComboBox for selecting the green space's type
    private ComboBox<String> cbType;

    @FXML
    // TextField for entering the green space's area
    private TextField txtArea;

    @FXML
    // TextField for entering the green space's street
    private TextField txtStreet;

    @FXML
    // TextField for entering the green space's street number
    private TextField txtStreetNumber;

    @FXML
    // TextField for entering the green space's postal code
    private TextField txtPostalCode;

    @FXML
    // TextField for entering the green space's city
    private TextField txtCity;

    @FXML
    // TextField for entering the green space's district
    private TextField txtDistrict;

    @FXML
    /**
     * Handles the action of going back to the GSM user interface.
     * It is triggered when the Back button is clicked.
     * It closes the current stage and loads the GsmGUI.
     */
    public void handleGsm() {
        loadUI("/GsmGUI.fxml");
    }

    // Repository for accessing authentication data
    private final AuthenticationRepository authenticationRepository;

    // Controller for managing green spaces
    private final GreenSpaceController controller;

    /**
     * Constructs a RegisterGreenSpaceGUI with a GreenSpaceController and an AuthenticationRepository.
     */
    public RegisterGreenSpaceGUI() {
        this.controller = new GreenSpaceController();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    @FXML
    /**
     * Initializes the user interface.
     * This method is called after all @FXML annotated members have been injected.
     * It sets up the ComboBox with the available green space types.
     */

    public void handleRegisterGreenSpace() {
        String name = txtName.getText();
        String typeName = cbType.getValue();
        String areaString = txtArea.getText();
        String streetNumberString = txtStreetNumber.getText();
        String streetName = txtStreet.getText();
        int streetNumber = Integer.parseInt(streetNumberString);
        String PostalCodeString = txtPostalCode.getText();
        if (!PostalCodeString.matches("[0-9]{4}-[0-9]{3}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid postal code. It should be in the format ´0000-000`.");
            alert.showAndWait();
            return;
        }
        String cityString = txtCity.getText();
        String districtString = txtDistrict.getText();

        Address address = new Address(streetName, streetNumber, PostalCodeString, cityString, districtString);

        if (name.isEmpty() || typeName == null || areaString.isEmpty() || streetName.isEmpty() || PostalCodeString.isEmpty() || cityString.isEmpty() || districtString.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        double area;
        try {
            area = Double.parseDouble(areaString);
            if (area <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Area must be a number bigger than 0");
            alert.showAndWait();
            return;
        }

        GreenSpaceTypeRepository type = GreenSpaceTypeRepository.valueOf(typeName.toUpperCase().replace(" ", "_"));
        String email = this.authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Collaborator gsm = organizationRepository.getOrganizationByEmployeeEmail(email).getCollaboratorByEmail(email);

        if (controller.registerGreenSpace(name, type, area, address, gsm)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Green space successfully added.");
            alert.showAndWait();

            // Get the current stage and close it
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add green space. Green space already exists.");
            alert.showAndWait();
        }
        UtilsGUI.loadUI("/GsmGUI.fxml");
    }

}