package com.ayoub_chaib.clinic_manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class MainController {
    public VBox consultationsContent;
    public VBox dashboardContent;
    public VBox patientsContent;
    @FXML private TabPane mainTabPane;
    @FXML private Tab patientsTab;
    @FXML private Tab consultationsTab;
    @FXML private Tab dashboardTab;

    @FXML private PatientController patientController;
    @FXML private ConsultationController consultationsContentController;
    @FXML private DashboardController dashboardController;

    @FXML
    public void initialize() {
        // When a tab is selected, refresh the corresponding view.
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldTab, newTab) -> {
                    if (newTab == patientsTab) {
                        patientController.refreshTable();
                    } else if (newTab == consultationsTab) {
                        consultationsContentController.refreshTable();
                    } else if (newTab == dashboardTab) {
                        dashboardController.loadDashboardData();
                    }
                }
        );
    }
}