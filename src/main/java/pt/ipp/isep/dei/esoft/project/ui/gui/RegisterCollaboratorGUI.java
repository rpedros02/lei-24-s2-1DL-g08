package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.format.DateTimeParseException;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI.*;

/**
 * This class provides a user interface for registering a collaborator.
 */
public class RegisterCollaboratorGUI {

    @FXML
    // TextField for entering the collaborator's name
    private TextField txtName;

    @FXML
    // TextField for entering the collaborator's birth date
    private TextField txtBirthDate;

    @FXML
    // TextField for entering the collaborator's admission date
    private TextField txtAdmissionDate;

    @FXML
    // TextField for entering the collaborator's mobile number
    private TextField txtMobileNumber;

    @FXML
    // TextField for entering the collaborator's email
    private TextField txtEmail;

    @FXML
    // TextField for entering the collaborator's tax payer number
    private TextField txtTaxPayerNumber;

    @FXML
    // ComboBox for selecting the collaborator's ID document type
    private ComboBox<IdDocType> cbIdType;

    @FXML
    // TextField for entering the collaborator's ID number
    private TextField txtIdNumber;

    @FXML
    // TextField for entering the collaborator's street
    private TextField txtStreet;

    @FXML
    // TextField for entering the collaborator's street number
    private TextField txtStreetNumber;

    @FXML
    // TextField for entering the collaborator's postal code
    private TextField txtPostalCode;

    @FXML
    // TextField for entering the collaborator's city
    private TextField txtCity;

    @FXML
    // TextField for entering the collaborator's district
    private TextField txtDistrict;

    @FXML
    // ComboBox for selecting the collaborator's job
    private ComboBox<String> cbJob;

    @FXML
    // Button for going back to the previous user interface
    private Button btnBack;

    // Controller for creating a collaborator
    private final CreateCollaboratorController createCollaboratorController;

    // Repository for accessing jobs
    private final JobRepository jobRepository;

    @FXML
    /**
     * Handles the action of returning to the HRM user interface.
     * It is triggered when the Back button is clicked.
     */
    public void handleReturn() {
        UtilsGUI.handleHRM(btnBack);
    }

    /**
     * Constructs a RegisterCollaboratorGUI with a CreateCollaboratorController and a JobRepository.
     */
    public RegisterCollaboratorGUI() {
        this.createCollaboratorController = new CreateCollaboratorController();
        this.jobRepository = Repositories.getInstance().getJobRepository();
    }

    @FXML
    /**
     * Initializes the user interface.
     * This method is called after all @FXML annotated members have been injected.
     * It sets up the ComboBoxes with the available ID document types and jobs.
     */
    public void initialize() {
        cbIdType.getItems().addAll(IdDocType.values());
        List<Job> jobs = jobRepository.getJobs();
        if(jobs.isEmpty()) {
            showAlert("There are no jobs registered. Please register a job before registering a collaborator.").showAndWait();
            handleHRM(btnBack);
        }
        for (Job job : jobs) {
            cbJob.getItems().add(job.getNameOfJob());
        }
    }

    @FXML
    /**
     * Handles the action of registering a collaborator.
     * It is triggered when the corresponding button is clicked.
     * It validates the input, creates a collaborator if the input is valid, and displays a message indicating the result.
     */
    public void handleRegisterCollaborator() {
        if (txtName.getText().isEmpty() || txtBirthDate.getText().isEmpty() || txtAdmissionDate.getText().isEmpty() || txtMobileNumber.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTaxPayerNumber.getText().isEmpty() || cbIdType.getValue() == null || txtIdNumber.getText().isEmpty() || txtStreet.getText().isEmpty() || txtStreetNumber.getText().isEmpty() || txtPostalCode.getText().isEmpty() || txtCity.getText().isEmpty() || txtDistrict.getText().isEmpty() || cbJob.getValue() == null) {
            UtilsGUI.showAlert("All fields are mandatory.").showAndWait();
            return;
        }

        IdDocType idDocType = cbIdType.getValue();
        String idNumber = txtIdNumber.getText();

        if (createCollaboratorController.getCollaborator(idNumber) != null) {
            UtilsGUI.showAlert("A collaborator with the same ID already exists. Please enter a different ID.").showAndWait();
            return;
        }

        String name = txtName.getText();
        Date birthDate;
        Date admissionDate;
        try {
            birthDate = Utils.dateFromString(txtBirthDate.getText());
            admissionDate = Utils.dateFromString(txtAdmissionDate.getText());
        } catch (DateTimeParseException e) {
            UtilsGUI.showAlert("Invalid date format. Please enter the date in the format yyyy-mm-dd.").showAndWait();
            return;
        }

        if (!admissionDate.isAfter(birthDate.plusYears(18))) {
            UtilsGUI.showAlert("Admission date must be at least 18 years after birth date. Please enter a valid admission date.").showAndWait();
            return;
        }

        String street = txtStreet.getText();
        int streetNumber = Integer.parseInt(txtStreetNumber.getText());
        String postalCode = txtPostalCode.getText();
        if (!postalCode.matches("[0-9]{4}-[0-9]{3}")) {
            UtilsGUI.showAlert("Invalid zip code. It should be in the format ´0000-000`.").showAndWait();

            return;
        }
        String city = txtCity.getText();
        String district = txtDistrict.getText();
        Address address = new Address(street, streetNumber, postalCode, city, district);
        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
        String email = txtEmail.getText();
        int taxPayerNumber = Integer.parseInt(txtTaxPayerNumber.getText());
        Job job =  jobRepository.getJobByName(cbJob.getValue());
        Task task = new Task();

        if (createCollaboratorController.createCollaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType, idNumber, address, job, task).isPresent()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Collaborator registered successfully");
            alert.showAndWait();

            Stage stage = (Stage) cbIdType.getScene().getWindow();
            loadUI("/HrmGUI.fxml");
            stage.close();
        } else {
            UtilsGUI.showAlert("An error occurred while registering the collaborator. Please try again.").showAndWait();
        }
    }
}