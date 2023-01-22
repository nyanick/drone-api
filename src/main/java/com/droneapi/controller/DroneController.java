package com.droneapi.controller;

import com.droneapi.model.Drone;
import com.droneapi.payload.DroneRegisterReponse;
import com.droneapi.payload.DroneRegisterRequest;
import com.droneapi.payload.LoadDroneRequest;
import com.droneapi.payload.LoadDroneResponse;
import com.droneapi.service.DroneService;
import com.droneapi.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/drone")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DroneRegisterReponse> registerDrone(@Valid @RequestBody DroneRegisterRequest payload) {
        DroneRegisterReponse drone = droneService.register(payload);
        return new ResponseEntity<DroneRegisterReponse>(drone, HttpStatus.OK);
    }

    @PutMapping(path= "/{serial_numuber}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Drone> updateDrone(@PathVariable String serial_numuber, @Valid @RequestBody DroneRegisterRequest payload) {
        Drone drone = droneService.update(serial_numuber, payload);
        return new ResponseEntity<Drone>(drone, HttpStatus.OK);
    }

    @GetMapping(path= "/{serial_numuber}")
    public ResponseEntity<?> getDrone(@PathVariable String serial_numuber) {
        Drone drone = droneService.getDrone(serial_numuber);
        return new ResponseEntity<>(drone, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getDrones() {
        List<Drone> drones = droneService.getAllDrones();
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @GetMapping(path= "/check_available_drones")
    public ResponseEntity<?> checkAvailableDrones() {
        List<Drone> drones = droneService.checkAvailableDrones();
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @PostMapping(path= "/load_medications", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoadDroneResponse> registerDrone(@Valid @RequestBody LoadDroneRequest payload) {
        LoadDroneResponse response = droneService.loadDrone(payload);
        return new ResponseEntity<LoadDroneResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping(path= "/{serial_numuber}")
    public ResponseEntity<?> removeDrone( @PathVariable String serial_numuber) {
        droneService.removeDrone(serial_numuber);
        return new ResponseEntity<>("Successfully deleted drone from the system", HttpStatus.OK);
    }

}
