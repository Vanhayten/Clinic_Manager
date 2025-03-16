package com.ayoub_chaib.clinic_manager.services;

import com.ayoub_chaib.clinic_manager.models.Patient;
import java.util.List;

public class PatientService {
    private final DatabaseService db = new DatabaseService();

    public void addPatient(Patient patient) {
        db.addPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return db.getAllPatients();
    }

    public List<String> getPatientIds() {
        return db.getPatientIds();
    }
}