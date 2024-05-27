package pt.ipp.isep.dei.esoft.project.ui.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Optional;

public class RegisterCollaboratorGUI {

    private CreateCollaboratorController controller;

    public void RegisterCollaboratorUI() {
        controller = new CreateCollaboratorController();
    }

    public CreateCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    @FXML
    private ComboBox<String> cbIdType;

    @FXML
    private ComboBox<String> cbJob;

    @FXML
    private Label lblMessage;

    @FXML
    private TextField txtAdmissionDate;

    @FXML
    private TextField txtBirthDate;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdNumber;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtTaxPayerNumber;

    @FXML
    private TextField txtZipCode;

    @FXML
    void handleRegisterCollaborator(ActionEvent event) {
        try {
            String name = txtName.getText();
            Date birthDate = new Date(txtBirthDate.getText());
            Date admissionDate = new Date(txtAdmissionDate.getText());
            int mobileNumber = Integer.parseInt(txtPhoneNumber.getText());
            String email = txtEmail.getText();
            int taxpayerNumber = Integer.parseInt(txtTaxPayerNumber.getText());
            IdDocType idType = IdDocType.valueOf(cbIdType.getValue().toUpperCase());
            String idNumber = txtIdNumber.getText();
            Address address = new Address(txtStreet.getText(), 4, (txtZipCode.getText()), txtCity.getText(), ""); // Add district if needed
            Job job = new Job(cbJob.getValue());

            Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(name, birthDate, admissionDate, mobileNumber, email, taxpayerNumber, idType, idNumber, address, job);

            if (collaborator.isPresent()) {
                lblMessage.setText("Collaborator registered successfully.");
            } else {
                lblMessage.setText("Failed to register collaborator.");
            }
        } catch (Exception e) {
            lblMessage.setText("Error: " + e.getMessage());
        }
    }

}
