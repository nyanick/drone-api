package com.droneapi.service;

import com.droneapi.model.Medication;
import com.droneapi.payload.MedicationRegisterRequest;
import com.droneapi.payload.MeditationRegisterReponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicationService {
    MeditationRegisterReponse register(MedicationRegisterRequest medication);

    Medication update(String code, MedicationRegisterRequest medication);

    Medication getMedication(String code);

    List<Medication> getAllMedications();

    void removeMedication(String code);
}
