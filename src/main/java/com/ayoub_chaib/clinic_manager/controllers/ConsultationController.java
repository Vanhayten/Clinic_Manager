package com.ayoub_chaib.clinic_manager.controllers;

import com.ayoub_chaib.clinic_manager.models.Consultation;
import com.ayoub_chaib.clinic_manager.models.Patient;
import com.ayoub_chaib.clinic_manager.services.ConsultationService;
import com.ayoub_chaib.clinic_manager.services.PatientService;
import com.ayoub_chaib.clinic_manager.utils.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultationController {
    private final ConsultationService consultationService = new ConsultationService();
    private final PatientService patientService = new PatientService();
    private final ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    @FXML private ComboBox<Patient> cmbPatients;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtHour;
    @FXML private TextField txtDiagnosis;
    @FXML private TextArea txtTreatment;
    @FXML private TextField txtFee;
    @FXML private TableView<Consultation> consultationTable;
    @FXML private TableColumn<Consultation, String> colConsultId;
    @FXML private TableColumn<Consultation, LocalDateTime> colDateTime;
    @FXML private TableColumn<Consultation, String> colDiagnosis;
    @FXML private TableColumn<Consultation, Double> colFee;

    @FXML
    public void initialize() {
        System.out.println("colFee initialized: " + (colFee != null));
        setupTable();
        loadConsultations();
        setupPatientComboBox();
    }

    private void setupTable() {
        colConsultId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colDateTime.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());
        colDiagnosis.setCellValueFactory(cellData -> cellData.getValue().diagnosisProperty());
        colFee.setCellValueFactory(cellData -> cellData.getValue().feeProperty().asObject());
        consultationTable.setItems(consultations);
    }

    private void loadConsultations() {
        consultations.setAll(consultationService.getAllConsultations());
    }

    private void setupPatientComboBox() {

        cmbPatients.setItems(patientService.getAllPatientsObservable());

        cmbPatients.setCellFactory(param -> new ListCell<Patient>() {
            @Override
            protected void updateItem(Patient patient, boolean empty) {
                super.updateItem(patient, empty);
                setText(empty ? null : patient.getFullName());
            }
        });

        cmbPatients.setConverter(new StringConverter<Patient>() {
            @Override
            public String toString(Patient patient) {
                return patient == null ? "" : patient.getFullName();
            }

            @Override
            public Patient fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleSaveConsultation() {
        try {
            if (!validateInput()) return;

            Patient selectedPatient = cmbPatients.getValue();
            if (selectedPatient == null) {
                AlertUtil.showError("Please select a patient!");
                return;
            }

            String patientId = selectedPatient.getId();

            LocalDate date = datePicker.getValue();
            LocalTime time = LocalTime.parse(txtHour.getText());
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            Consultation consultation = new Consultation();
            consultation.setPatientId(patientId);
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