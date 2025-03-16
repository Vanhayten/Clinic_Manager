module com.ayoub_chaib.clinic_manager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ayoub_chaib.clinic_manager to javafx.fxml;
    exports com.ayoub_chaib.clinic_manager;
}