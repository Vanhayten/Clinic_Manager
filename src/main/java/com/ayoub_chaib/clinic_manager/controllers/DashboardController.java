package com.ayoub_chaib.clinic_manager.controllers;

import com.ayoub_chaib.clinic_manager.models.Consultation;
import com.ayoub_chaib.clinic_manager.services.ConsultationService;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class DashboardController {
    @FXML private BarChart<String, Number> barChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    private final ConsultationService consultationService = new ConsultationService();

    public void loadDashboardData() {
        List<Consultation> consultations = consultationService.getAllConsultations();

        // Group consultations by month (using the short month name)
        Map<String, Long> monthlyCounts = consultations.stream()
                .collect(Collectors.groupingBy(c -> c.getDateTime().getMonth()
                                .getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        Collectors.counting()));

        // Create a new series and add data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Consultations");

        monthlyCounts.forEach((month, count) -> {
            series.getData().add(new XYChart.Data<>(month, count));
        });

        barChart.getData().clear();
        barChart.getData().add(series);
    }
}
