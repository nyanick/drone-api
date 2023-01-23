package com.droneapi.service.impl;

import com.droneapi.model.Drone;
import com.droneapi.model.Medication;
import com.droneapi.payload.MedicationRegisterRequest;
import com.droneapi.payload.MeditationRegisterReponse;
import com.droneapi.repository.MedicationRepository;
import com.droneapi.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MedicationServiceImp implements MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public MeditationRegisterReponse register(MedicationRegisterRequest medication) {
        MeditationRegisterReponse response = new MeditationRegisterReponse();
        Medication new_medication = new Medication();
        new_medication.setName(medication.getName());
        new_medication.setCode(medication.getCode());
        new_medication.setWeight(medication.getWeight());
        new_medication.setImage(medication.getImage());

        Medication saved_new_medication = medicationRepository.save(new_medication);
        response.setSuccess(true);
        response.setMessage("Medication successfully saved");
        response.setData(saved_new_medication);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }

    @Override
    public Medication update(String code, MedicationRegisterRequest medication) {
        Medication med =  medicationRepository.findById(code).get();
        if(med == null){
            return null;//invalid code for medication
        }
        med.setName(medication.getName());
        med.setWeight(medication.getWeight());
        med.setImage(medication.getImage());
        return medicationRepository.save(med);
    }

    @Override
    public Medication getMedication(String code) {
        return medicationRepository.findById(code).orElse(null);
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public void removeMedication(String code) {
        medicationRepository.deleteById(code);
    }
}
