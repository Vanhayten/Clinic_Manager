package com.ayoub_chaib.clinic_manager.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Patient {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty fullName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<>();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();

    public Patient() {}

    public Patient(String id, String fullName, LocalDate dateOfBirth) {
        this.id.set(id);
        this.fullName.set(fullName);
        this.dateOfBirth.set(dateOfBirth);
    }

    // Property getters
    public StringProperty idProperty() { return id; }
    public StringProperty fullNameProperty() { return fullName; }
    public ObjectProperty<LocalDate> dateOfBirthProperty() { return dateOfBirth; }
    public StringProperty genderProperty() { return gender; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty addressProperty() { return address; }

    // Getters
    public String getId() { return id.get(); }
    public String getFullName() { return fullName.get(); }
    public LocalDate getDateOfBirth() { return dateOfBirth.get(); }
    public String getGender() { return gender.get(); }
    public String getPhone() { return phone.get(); }
    public String getAddress() { return address.get(); }

    // Setters
    public void setId(String id) { this.id.set(id); }
    public void setFullName(String fullName) { this.fullName.set(fullName); }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth.set(dateOfBirth); }
    public void setGender(String gender) { this.gender.set(gender); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public void setAddress(String address) { this.address.set(address); }
}