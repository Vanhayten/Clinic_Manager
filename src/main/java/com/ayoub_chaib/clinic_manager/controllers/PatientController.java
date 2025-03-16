package com.ayoub_chaib.clinic_manager.controllers;

import com.ayoub_chaib.clinic_manager.models.Patient;
import com.ayoub_chaib.clinic_manager.services.PatientService;
import com.ayoub_chaib.clinic_manager.utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PatientController {
    private final PatientService patientService = new PatientService();
    private final ObservableList<Patient> patients = FXCollections.observableArrayList();

    @FXML private TextField txtFullName;
    @FXML private DatePicker dateBirth;
    @FXML private ComboBox<String> cmbGender;
    @FXML private TextField txtPhone;
    @FXML private TextArea txtAddress;
    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, String> colId;
    @FXML private TableColumn<Patient, String> colName;

    @FXML
    public void initialize() {
        setupTable();
        loadPatients();
        cmbGender.getItems().addAll("Male", "Female", "Other");
    }

    private void setupTable() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        patientTable.setItems(patients);
    }

    private void loadPatients() {
        patients.setAll(patientService.getAllPatients());
    }

    @FXML
    private void handleSavePatient() {
        try {
            if (!validateInput()) return;

            Patient patient = new Patient();
            patient.setFullName(txtFullName.getText());
            patient.setDateOfBirth(dateBirth.getValue());
            patient.setGender(cmbGender.getValue());
            patient.setPhone(txtPhone.getText());
            patient.setAddress(txtAddress.getText());

            patientService.addPatient(patient);
            refreshTable();
            clearForm();
            AlertUtil.showSuccess("Patient saved successfully!");
        } catch (Exception e) {
            AlertUtil.showError("Error saving patient: " + e.getMessage());
        }
    }

    public void refreshTable() {
        patients.setAll(patientService.getAllPatients());
        patientTable.refresh();
    }

    private boolean validateInput() {
        if (txtFullName.getText().isEmpty()) {
            AlertUtil.showError("Full name is required!");
            return false;
        }
        return true;
    }

    @FXML
    private void clearForm() {
        txtFullName.clear();
        dateBirth.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        txtPhone.clear();
        txtAddress.clear();
    }
}