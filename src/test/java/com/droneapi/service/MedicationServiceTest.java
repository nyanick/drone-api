package com.droneapi.service;

import com.droneapi.model.Drone;
import com.droneapi.model.Medication;
import com.droneapi.payload.DroneRegisterRequest;
import com.droneapi.payload.MedicationRegisterRequest;
import com.droneapi.payload.MeditationRegisterReponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicationServiceTest {

    @Autowired
    MedicationService medicationService;

    /*
    Register test for medication
     */
    @Test
    void register() {
        MedicationRegisterRequest med1 = new MedicationRegisterRequest();
        med1.setName("Paracetamol");
        med1.setWeight(200);
        med1.setCode("CD2");
        med1.setImage("paracetamol.png");
        Medication new_mde = medicationService.register(med1).getData();
        Assertions.assertEquals(new_mde.getCode(), new_mde.getCode(), "Registration of Medication is ok");
    }

    /**
     * Test case to get a single medication
     */
    @Test
    void getMedication() {
        MedicationRegisterRequest med1 = new MedicationRegisterRequest();
        med1.setName("Paracetamol");
        med1.setWeight(200);
        med1.setCode("CD2");
        med1.setImage("paracetamol.png");
        medicationService.register(med1);
        Assertions.assertEquals(medicationService.getMedication("CD2").getName(), med1.getName());

    }

    @Test
    void getAllMedications() {
        MedicationRegisterRequest med1 = new MedicationRegisterRequest();
        med1.setName("Paracetamol");
        med1.setWeight(200);
        med1.setCode("CD2");
        med1.setImage("paracetamol.png");
        medicationService.register(med1);
        Assertions.assertEquals(medicationService.getAllMedications().get(0).getName(), med1.getName());
    }

    @Test
    void removeMedication() {
        MedicationRegisterRequest med1 = new MedicationRegisterRequest();
        med1.setName("Paracetamol");
        med1.setWeight(200);
        med1.setCode("CD2");
        med1.setImage("paracetamol.png");
        medicationService.register(med1);
        medicationService.removeMedication("CD2");
        Assertions.assertEquals(medicationService.getMedication("CD2"), null);
    }
}