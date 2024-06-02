package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceTypeRepository;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

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
    private TextField txtZipCode;

    @FXML
    private TextField txtCity;

    private final AuthenticationRepository authenticationRepository;

    private GreenSpaceController controller;

    public RegisterGreenSpaceGUI() {
        this.controller = new GreenSpaceController();
        this.authenticationRepository= Repositories.getInstance().getAuthenticationRepository();
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
        String streetName = txtStreet.getText();
        String zipCodeString = txtZipCode.getText();
        if (!zipCodeString.matches("[0-9]{4}-[0-9]{3}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid zip code. It should be in the format ´0000-000`.");
            alert.showAndWait();
            return;
        }
        String cityString = txtCity.getText();

        Address address = new Address(streetName, zipCodeString, cityString);

        if (name.isEmpty() || typeName == null || areaString.isEmpty() || streetName.isEmpty() || zipCodeString.isEmpty() || cityString.isEmpty()){
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

        if (controller.registerGreenSpace(name, type, area, email, address)) { // Pass the GSM to the method
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
    }
}