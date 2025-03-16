package com.ayoub_chaib.clinic_manager.controllers;

import com.ayoub_chaib.clinic_manager.models.Consultation;
import com.ayoub_chaib.clinic_manager.services.ConsultationService;
import com.ayoub_chaib.clinic_manager.services.PatientService;
import com.ayoub_chaib.clinic_manager.utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultationController {
    private final ConsultationService consultationService = new ConsultationService();
    private final PatientService patientService = new PatientService();
    private final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    @FXML private ComboBox<String> cmbPatients;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtHour;
    @FXML private TextField txtDiagnosis;
    @FXML private TextArea txtTreatment;
    @FXML private TextField txtFee;
    @FXML private TableView<Consultation> consultationTable;
    @FXML private TableColumn<Consultation, String> colConsultId;
    @FXML private TableColumn<Consultation, LocalDateTime> colDateTime;
    @FXML private TableColumn<Consultation, String> colDiagnosis;

    @FXML
    public void initialize() {
        setupTable();
        loadConsultations();
        loadPatients();
    }

    private void setupTable() {
        colConsultId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colDateTime.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());
        colDiagnosis.setCellValueFactory(cellData -> cellData.getValue().diagnosisProperty());
        consultationTable.setItems(consultations);
    }

    private void loadConsultations() {
        consultations.setAll(consultationService.getAllConsultations());
    }

    private void loadPatients() {
        cmbPatients.getItems().setAll(patientService.getPatientIds());
    }

    @FXML
    private void handleSaveConsultation() {
        try {
            if (!validateInput()) return;

            LocalDate date = datePicker.getValue();
            LocalTime time = LocalTime.parse(txtHour.getText());
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            Consultation consultation = new Consultation();
            consultation.setPatientId(cmbPatients.getValue());
            consultation.setDateTime(dateTime);
            consultation.setDiagnosis(txtDiagnosis.getText());
            consultation.setTreatment(txtTreatment.getText());
            consultation.setFee(Double.parseDouble(txtFee.getText()));

            consultationService.addConsultation(consultation);
            refreshTable();
            clearForm();
            AlertUtil.showSuccess("Consultation saved successfully!");
        } catch (Exception e) {
            AlertUtil.showError("Error saving consultation: " + e.getMessage());
        }
    }

    public void refreshTable() {
        consultations.setAll(consultationService.getAllConsultations());
        consultationTable.refresh();
    }

    private boolean validateInput() {
        if (cmbPatients.getValue() == null) {
            AlertUtil.showError("Please select a patient!");
            return false;
        }
        if (datePicker.getValue() == null) {
            AlertUtil.showError("Date is required!");
            return false;
        }
        if (!txtHour.getText().matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
            AlertUtil.showError("Invalid time format! Use HH:mm");
            return false;
        }
        return true;
    }

    @FXML
    private void clearForm() {
        cmbPatients.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        txtHour.clear();
        txtDiagnosis.clear();
        txtTreatment.clear();
        txtFee.clear();
    }
}