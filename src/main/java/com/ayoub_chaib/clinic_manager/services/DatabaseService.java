package com.ayoub_chaib.clinic_manager.services;

import com.ayoub_chaib.clinic_manager.models.Consultation;
import com.ayoub_chaib.clinic_manager.models.Patient;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private final ConcurrentHashMap<String, Patient> patients = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Consultation> consultations = new ConcurrentHashMap<>();

    // Patient operations
    public void addPatient(Patient patient) {
        patients.put(patient.getId(), patient);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }

    public List<String> getPatientIds() {
        return new ArrayList<>(patients.keySet());
    }

    // Consultation operations
    public void addConsultation(Consultation consultation) {
        consultations.put(consultation.getId(), consultation);
    }

    public List<Consultation> getAllConsultations() {
        return new ArrayList<>(consultations.values());
    }
}