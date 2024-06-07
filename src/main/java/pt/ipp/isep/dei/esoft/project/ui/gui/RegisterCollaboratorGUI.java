//package pt.ipp.isep.dei.esoft.project.ui.gui;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
//import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
//import pt.ipp.isep.dei.esoft.project.domain.Address;
//import pt.ipp.isep.dei.esoft.project.domain.Job;
//import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//
//public class RegisterCollaboratorGUI {
//
//    @FXML
//    private TextField txtName;
//    @FXML
//    private TextField txtBirthDate;
//    @FXML
//    private TextField txtAdmissionDate;
//    @FXML
//    private TextField txtMobileNumber;
//    @FXML
//    private TextField txtEmail;
//    @FXML
//    private TextField txtTaxPayerNumber;
//    @FXML
//    private ComboBox<IdDocType> cbIdType;
//    @FXML
//    private TextField txtIdNumber;
//    @FXML
//    private TextField txtStreet;
//    @FXML
//    private TextField txtStreetNumber;
//    @FXML
//    private TextField txtPostalCode;
//    @FXML
//    private TextField txtCity;
//    @FXML
//    private TextField txtDistrict;
//    @FXML
//    private ComboBox<Job> cbJob;
//
//    private CreateCollaboratorController createCollaboratorController;
//    private JobRepository jobRepository;
//
//    public RegisterCollaboratorGUI() {
//        this.createCollaboratorController = new CreateCollaboratorController();
//        this.jobRepository = new JobRepository();
//    }
//
//    @FXML
//    public void initialize() {
//        cbIdType.getItems().addAll(IdDocType.values());
//        cbJob.getItems().addAll(jobRepository.getJobs());
//    }
//
//    @FXML
//    public void handleRegisterCollaborator() {
//        if (txtName.getText().isEmpty() || txtBirthDate.getText().isEmpty() || txtAdmissionDate.getText().isEmpty() || txtMobileNumber.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTaxPayerNumber.getText().isEmpty() || cbIdType.getValue() == null || txtIdNumber.getText().isEmpty() ||  txtStreet.getText().isEmpty() || txtStreetNumber.getText().isEmpty() ||txtPostalCode.getText().isEmpty() || txtCity.getText().isEmpty() ||  txtDistrict.getText().isEmpty() ||cbJob.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("All fields must be filled");
//            alert.showAndWait();
//            return;
//        }
//
//        IdDocType idDocType = cbIdType.getValue();
//        String idNumber = txtIdNumber.getText();
//
//        if (!idDocType.isValidNumber(idNumber)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid ID number. Please enter a valid ID number.");
//            alert.showAndWait();
//            return;
//        }
//
//        if (createCollaboratorController.Collaborator(idNumber) != null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("A Collaborator with this ID number already exists.");
//            alert.showAndWait();
//            return;
//        }
//
//        String name = txtName.getText();
//        LocalDate birthDate;
//        LocalDate admissionDate;
//        try {
//            birthDate = LocalDate.parse(txtBirthDate.getText());
//            admissionDate = LocalDate.parse(txtAdmissionDate.getText());
//        } catch (DateTimeParseException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
//            alert.showAndWait();
//            return;
//        }
//
//        if (admissionDate.isBefore(birthDate.plusYears(16))) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Admission date must be at least 16 years after birth date. Please enter a valid admission date.");
//            alert.showAndWait();
//            return;
//        }
//
//        String street = txtStreet.getText();
//        String streetNumber = txtStreetNumber.getText();
//        String postalCode = txtPostalCode.getText();
//        if (!postalCode.matches("[0-9]{4}-[0-9]{3}")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid zip code. It should be in the format ´0000-000`.");
//            alert.showAndWait();
//            return;
//        }
//        String city = txtCity.getText();
//        String district = txtDistrict.getText();
//        Address address = new Address(street, streetNumber, postalCode, city, district);
//        int mobileNumber = Integer.parseInt(txtMobileNumber.getText());
//        String email = txtEmail.getText();
//        int taxPayerNumber = Integer.parseInt(txtTaxPayerNumber.getText());
//        Job job = cbJob.getValue();
//
//        if (collaboratorController.registerCollaborator(name, birthDate, admissionDate, address, mobileNumber, email, taxPayerNumber, idNumber, job, idType) != null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success");
//            alert.setHeaderText(null);
//            alert.setContentText("Collaborator registered successfully");
//            alert.showAndWait();
//
//            // Close the current stage
//            Stage stage = (Stage) cbIdType.getScene().getWindow();
//            stage.close();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Collaborator not registered");
//            alert.showAndWait();
//        }
//    }
//}