package com.ayoub_chaib.clinic_manager.services;

import com.ayoub_chaib.clinic_manager.models.Consultation;
import java.util.List;
import java.util.UUID;

public class ConsultationService {
    private final DatabaseService db = DatabaseService.getInstance();

    public void addConsultation(Consultation consultation) {
        if(consultation.getId() == null || consultation.getId().isEmpty()){
            consultation.setId(UUID.randomUUID().toString());
        }
        db.addConsultation(consultation);
    }

    public List<Consultation> getAllConsultations() {
        return db.getAllConsultations();
    }
}