package com.droneapi.service;

import com.droneapi.model.Drone;
import com.droneapi.model.Medication;
import com.droneapi.payload.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DroneServiceTest {
    @Autowired
    DroneService droneService;

    @Autowired
    MedicationService medicationService;

    /**
     * Unit test for registraTION OF DRONE
     */
    @Test
    void register() {
        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        Drone new_drone = droneService.register(drone).getData();
        Assertions.assertEquals(new_drone.getSerialNumber(), drone.getSerialNumber(), "Registration of drone works ok");
    }

    /**
     * Testing the constraints on the weight limit of a drone
     */

    @Test
    void ConstraintsOnDroneRegistration() {
        Throwable exception = assertThrows(Exception.class,
                ()->{
                    DroneRegisterRequest drone = new DroneRegisterRequest();
                    drone.setSerialNumber("SN1");
                    drone.setBattery(100.0);
                    drone.setState("IDLE");
                    drone.setModel("Lightweight");
                    drone.setWeightLimit(8000); //
                    droneService.register(drone).getData();
            }, "Throws an exception when the contraints are not respected, the weight limit is 8000 with a constraint max of 500" );
    }


    /**
     * Getting a single Drone by serial number test
     */
    @Test
    void getDrone() {
        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);
        Assertions.assertEquals(droneService.getDrone("SN1").getSerialNumber(), drone.getSerialNumber());

    }

    @Test
    void getAllDrones() {
        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);
        Assertions.assertEquals(droneService.getAllDrones().get(0).getSerialNumber(), drone.getSerialNumber());
    }

    @Test
    void removeDrone() {
        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);
        droneService.removeDrone("SN1");
        Assertions.assertEquals(droneService.getDrone("SN1"), null);
    }

    /**
     * We test loading of a drone, with new medications,
     * we would insert a new medication object
     */
    @Test
    void loadDroneWithNewMedication() {

        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);

        LoadDroneRequest loadRequest = new LoadDroneRequest();
        loadRequest.setDrone_serial_num("SN1");
        /*
        We create new medications
         */
        List<MedicationObj> new_meds = new ArrayList<>();
        MedicationObj med1 = new MedicationObj();
        med1.setName("Paracetamol");
        med1.setWeight(200);
        med1.setCode("CN2");
        med1.setImage("paracetamol.png");
        new_meds.add(med1);
        loadRequest.setNew_medications(new_meds);

        droneService.loadDrone(loadRequest);
        Assertions.assertEquals(droneService.getDrone("SN1").getMedications().size(),1, "New medication has been loaded successfully");

    }

    /**
     * We test loading of a drone, with existing medications,
     * we create medications in our system and later pass their code into the
     * load request payload
     */
    @Test
    void loadDroneWithExistingMedication() {

        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);

        LoadDroneRequest loadRequest = new LoadDroneRequest();
        loadRequest.setDrone_serial_num("SN1");
        /*
        We create new medications
         */
        MedicationRegisterRequest med1 = new MedicationRegisterRequest();
        med1.setName("Paracetamol");
        med1.setWeight(200);
        med1.setCode("CN1");
        med1.setImage("paracetamol.png");
        medicationService.register(med1);

        List<String> existing_medications = new ArrayList<>();
        existing_medications.add("CN1");
        loadRequest.setExisting_medication_codes(existing_medications);

        droneService.loadDrone(loadRequest);
        Assertions.assertEquals(droneService.getDrone("SN1").getMedications().size(),1, "New medication has been loaded successfully");

    }

    @Test
    void loadDroneWithExceedingWeight() {

        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);

        LoadDroneRequest loadRequest = new LoadDroneRequest();
        loadRequest.setDrone_serial_num("SN1");
        /*
        We create new medications
         */
        List<MedicationObj> new_meds = new ArrayList<>();
        MedicationObj med1 = new MedicationObj();
        med1.setName("Paracetamol");
        med1.setWeight(550);
        med1.setCode("CN1");
        med1.setImage("paracetamol.png");
        new_meds.add(med1);
        loadRequest.setNew_medications(new_meds);

        LoadDroneResponse response = droneService.loadDrone(loadRequest);
        Assertions.assertEquals(response.isSuccess(),false, "Constraints are the level of the weight");

    }

    @Test
    void loadDroneNotAvailable() {

        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("RETURNING");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);

        LoadDroneRequest loadRequest = new LoadDroneRequest();
        loadRequest.setDrone_serial_num("SN1");
        /*
        We create new medications
         */
        List<MedicationObj> new_meds = new ArrayList<>();
        MedicationObj med1 = new MedicationObj();
        med1.setName("Paracetamol");
        med1.setWeight(550);
        med1.setCode("CN1");
        med1.setImage("paracetamol.png");
        new_meds.add(med1);
        loadRequest.setNew_medications(new_meds);

        LoadDroneResponse response = droneService.loadDrone(loadRequest);
        Assertions.assertEquals(response.isSuccess(),false, "Drone is currently not available");

    }

    @Test
    void checkAvailableDrones() {
        /*
        We create a drone and set it to idle, indicating it is available
         */
        DroneRegisterRequest drone = new DroneRegisterRequest();
        drone.setSerialNumber("SN1");
        drone.setBattery(100.0);
        drone.setState("IDLE");
        drone.setModel("Lightweight");
        drone.setWeightLimit(400);
        droneService.register(drone);

        DroneRegisterRequest drone1 = new DroneRegisterRequest();
        drone1.setSerialNumber("SN2");
        drone1.setBattery(100.0);
        drone1.setState("LOADING");
        drone1.setModel("Lightweight");
        drone1.setWeightLimit(400);
        droneService.register(drone1);

        Assertions.assertEquals(droneService.checkAvailableDrones().get(0).getSerialNumber(), drone.getSerialNumber(), "The Drone we inserted is the one available");
    }
}