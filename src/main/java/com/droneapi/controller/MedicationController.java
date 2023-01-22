package com.droneapi.controller;

import com.droneapi.model.Medication;
import com.droneapi.payload.*;
import com.droneapi.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MeditationRegisterReponse> registermedication(@Valid @RequestBody MedicationRegisterRequest payload) {
        MeditationRegisterReponse medication = medicationService.register(payload);
        return new ResponseEntity<MeditationRegisterReponse>(medication, HttpStatus.OK);
    }

    @PutMapping(path= "/{code}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Medication> updateMedication(@PathVariable String code, @Valid @RequestBody MedicationRegisterRequest payload) {
        Medication medication = medicationService.update(code, payload);
        return new ResponseEntity<Medication>(medication, HttpStatus.OK);
    }

    @GetMapping(path= "/{code}")
    public ResponseEntity<Medication> getMedication(@PathVariable String code) {
        Medication medication = medicationService.getMedication(code);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMedications() {
        List<Medication> medications = medicationService.getAllMedications();
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    @DeleteMapping(path= "/{code}")
    public ResponseEntity<?> removeMedication( @PathVariable String code) {
        medicationService.removeMedication(code);
        return new ResponseEntity<>("Successfully deleted Medication from the system", HttpStatus.OK);
    }


}
