package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

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

    @FXML
    public void handleGsm() {
        handleGSM(btnBack);
    }


    private final AuthenticationRepository authenticationRepository;

    private final GreenSpaceController controller;

    public RegisterGreenSpaceGUI() {
        this.controller = new GreenSpaceController();
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    @FXML
    public void initialize() {
        cbType.getItems().addAll(GreenSpaceTypeRepository.getGreenSpaceTypes());
    }

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
            showAlert("Invalid postal code. It should be in the format ´0000-000`.").showAndWait();
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
            showAlert("Invalid area. Please enter a positive number.").showAndWait();
            return;
        }

        GreenSpaceTypeRepository type = GreenSpaceTypeRepository.valueOf(typeName.toUpperCase().replace(" ", "_"));
        String email = this.authenticationRepository.getCurrentUserSession().getUserId().getEmail();
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Collaborator gsm = organizationRepository.getOrganizationByEmployeeEmail(email).getCollaboratorByEmail(email);
        if (controller.registerGreenSpace(name, type, area, address, gsm)) {
            showSuccess("Green space added successfully.").showAndWait();
        } else {
            showAlert("An error occurred. Please try again.").showAndWait();
        }
        handleGsm();
    }

}