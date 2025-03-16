package com.ayoub_chaib.clinic_manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainController {
    @FXML private TabPane mainTabPane;
    @FXML private PatientController patientController;
    @FXML private ConsultationController consultationController;

    @FXML
    public void initialize() {
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldTab, newTab) -> {
                    if (newTab.getId().equals("patientsTab")) {
                        patientController.refreshTable();
                    } else if (newTab.getId().equals("consultationsTab")) {
                        consultationController.refreshTable();
                    }
                }
        );
    }
}