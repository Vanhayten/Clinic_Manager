    package com.ayoub_chaib.clinic_manager.models;

    import javafx.beans.property.*;
    import java.time.LocalDateTime;

    public class Consultation {
        private final StringProperty id = new SimpleStringProperty();
        private final ObjectProperty<LocalDateTime> dateTime = new SimpleObjectProperty<>();
        private final StringProperty patientId = new SimpleStringProperty();
        private final StringProperty diagnosis = new SimpleStringProperty();
        private final StringProperty treatment = new SimpleStringProperty();
        private final DoubleProperty fee = new SimpleDoubleProperty();

        public Consultation() {}

        public Consultation(String id, LocalDateTime dateTime, String patientId) {
            this.id.set(id);
            this.dateTime.set(dateTime);
            this.patientId.set(patientId);
        }

        // Property getters
        public StringProperty idProperty() { return id; }
        public ObjectProperty<LocalDateTime> dateTimeProperty() { return dateTime; }
        public StringProperty patientIdProperty() { return patientId; }
        public StringProperty diagnosisProperty() { return diagnosis; }
        public StringProperty treatmentProperty() { return treatment; }
        public DoubleProperty feeProperty() { return fee; }

        // Getters
        public String getId() { return id.get(); }
        public LocalDateTime getDateTime() { return dateTime.get(); }
        public String getPatientId() { return patientId.get(); }
        public String getDiagnosis() { return diagnosis.get(); }
        public String getTreatment() { return treatment.get(); }
        public double getFee() { return fee.get(); }

        // Setters
        public void setId(String id) { this.id.set(id); }
        public void setDateTime(LocalDateTime dateTime) { this.dateTime.set(dateTime); }
        public void setPatientId(String patientId) { this.patientId.set(patientId); }
        public void setDiagnosis(String diagnosis) { this.diagnosis.set(diagnosis); }
        public void setTreatment(String treatment) { this.treatment.set(treatment); }
        public void setFee(double fee) { this.fee.set(fee); }
    }