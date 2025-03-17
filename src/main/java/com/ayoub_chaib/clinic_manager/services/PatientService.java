package com.ayoub_chaib.clinic_manager.services;

import com.ayoub_chaib.clinic_manager.models.Patient;
import java.util.List;
import java.util.UUID;

public class PatientService {
    private final DatabaseService db = DatabaseService.getInstance();

    public void addPatient(Patient patient) {
        if(patient.getId() == null || patient.getId().isEmpty()){
            patient.setId(UUID.randomUUID().toString());
        }
        db.addPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return db.getAllPatients();
    }

    public List<String> getPatientIds() {
        return db.getPatientIds();
    }
}