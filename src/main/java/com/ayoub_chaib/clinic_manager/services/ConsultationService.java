package com.ayoub_chaib.clinic_manager.services;

import com.ayoub_chaib.clinic_manager.models.Consultation;
import java.util.List;

public class ConsultationService {
    private final DatabaseService db = new DatabaseService();

    public void addConsultation(Consultation consultation) {
        db.addConsultation(consultation);
    }

    public List<Consultation> getAllConsultations() {
        return db.getAllConsultations();
    }
}