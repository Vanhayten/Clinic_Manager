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

    @FXML private PatientController patientsContentController;
    @FXML private ConsultationController consultationsContentController;
    @FXML private DashboardController dashboardContentController;

    @FXML
    public void initialize() {
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldTab, newTab) -> {
                    if (newTab == patientsTab) {
                        patientsContentController.refreshTable();
                    } else if (newTab == consultationsTab) {
                        consultationsContentController.refreshTable();
                    } else if (newTab == dashboardTab) {
                        dashboardContentController.loadDashboardData();
                    }
                }
        );
    }
}