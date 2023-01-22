package com.droneapi.service.impl;

import com.droneapi.model.Medication;
import com.droneapi.payload.MedicationRegisterRequest;
import com.droneapi.payload.MeditationRegisterReponse;
import com.droneapi.service.MedicationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicationServiceImp implements MedicationService {

    @Override
    public MeditationRegisterReponse register(MedicationRegisterRequest medication) {
        return null;
    }

    @Override
    public Medication update(String code, MedicationRegisterRequest medication) {
        return null;
    }

    @Override
    public Medication getMedication(String code) {
        return null;
    }

    @Override
    public List<Medication> getAllMedications() {
        return null;
    }

    @Override
    public void removeMedication(String code) {

    }
}
