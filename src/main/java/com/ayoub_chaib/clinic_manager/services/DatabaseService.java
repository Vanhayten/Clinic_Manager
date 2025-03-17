package com.ayoub_chaib.clinic_manager.services;

import com.ayoub_chaib.clinic_manager.models.Patient;
import com.ayoub_chaib.clinic_manager.models.Consultation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static final DatabaseService instance = new DatabaseService();

    private final ConcurrentHashMap<String, Patient> patients = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Consultation> consultations = new ConcurrentHashMap<>();

    private final ObservableList<Patient> patientsList = FXCollections.observableArrayList();

    private DatabaseService() {}

    public static DatabaseService getInstance() {
        return instance;
    }

    public void addPatient(Patient patient) {
        patients.put(patient.getId(), patient);
        patientsList.add(patient);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }

    public ObservableList<Patient> getAllPatientsObservable() {
        return patientsList;
    }

    // Consultation operations
    public void addConsultation(Consultation consultation) {
        consultations.put(consultation.getId(), consultation);
    }

    public List<Consultation> getAllConsultations() {
        return new ArrayList<>(consultations.values());
    }
}