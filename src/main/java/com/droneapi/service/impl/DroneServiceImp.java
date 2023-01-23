package com.droneapi.service.impl;

import com.droneapi.model.Drone;
import com.droneapi.model.Medication;
import com.droneapi.model.Model;
import com.droneapi.model.State;
import com.droneapi.payload.*;
import com.droneapi.repository.DroneRepository;
import com.droneapi.repository.MedicationRepository;
import com.droneapi.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DroneServiceImp implements DroneService {

    @Autowired
    DroneRepository droneRepository;
    @Autowired
    MedicationRepository medicationRepository;

    /**
     * creation of Drone
     * @param drone
     * @return
     */
    @Override
    public DroneRegisterReponse register(DroneRegisterRequest drone) {
        DroneRegisterReponse response = new DroneRegisterReponse();
        response.setTimestamp(LocalDateTime.now());
        Drone new_drone = new Drone();
        new_drone.setBattery(drone.getBattery());
        new_drone.setSerialNumber(drone.getSerialNumber());
        List states = Arrays.asList(State.values());
        List models = Arrays.asList(Model.values());
        if(!states.stream().map(o->o.toString()).toList().contains(drone.getState().toUpperCase())){
            response.setSuccess(false);
            response.setMessage("invalid value state");
            return response;
        }

        if(!models.stream().map(o->o.toString().toUpperCase()).toList().contains(drone.getModel().toUpperCase())){
            response.setSuccess(false);
            response.setMessage("invalid value Model");
            return response;
        }

        new_drone.setState(State.valueOf(drone.getState().toUpperCase()));
        new_drone.setModel(Model.valueOf(drone.getModel()));
        new_drone.setWeightLimit(drone.getWeightLimit());
        Drone saved_drone = droneRepository.save(new_drone);
        response.setData(saved_drone);
        response.setSuccess(true);
        response.setMessage("Successfully register drone");
        return response;
    }

    /**
     * Update an existing drone in the db
     * @param serial_num
     * @param drone
     * @return
     */
    @Override
    public Drone update(String serial_num, DroneRegisterRequest drone) {
        Drone new_drone = droneRepository.findById(serial_num).get();
        System.out.println("Thisis it -------------");
        System.out.println(new_drone);
        System.out.println("Thisis it -------------");

        if(new_drone == null){
            return null;//invalid serial_num
        }
        new_drone.setBattery(drone.getBattery());
        List states = Arrays.asList(State.values());
        List models = Arrays.asList(Model.values());

        if(!states.contains(drone.getState().toUpperCase())){
            return null;//invalid state value
        }
        if(!models.stream().map(o->o.toString().toUpperCase()).toList().contains(drone.getModel().toUpperCase())){
            return null;//invalid model value
        }

        new_drone.setModel(Model.valueOf(drone.getModel()));
        new_drone.setState(State.valueOf(drone.getState().toUpperCase()));
        new_drone.setWeightLimit(drone.getWeightLimit());
        Drone saved_drone = droneRepository.save(new_drone);
        return saved_drone;

    }

    @Override
    public Drone getDrone(String serial_num) {
        return droneRepository.findById(serial_num).orElse(null);
    }

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public void removeDrone(String serial_num) {
        droneRepository.deleteById(serial_num);
    }

    /**
     * Loading a drone
     * @param request
     * @return
     */
    @Override
    public LoadDroneResponse loadDrone(LoadDroneRequest request) {
        List<Medication> medications = new ArrayList<>();
        LoadDroneResponse response = new LoadDroneResponse();
        response.setSuccess(false);
        response.setTimestamp(LocalDateTime.now());
        int weight = 0;
        Drone drone = droneRepository.findById(request.getDrone_serial_num()).get();
        if(drone == null){
            response.setMessage("Invalid serial number : "+request.getDrone_serial_num());
            return response;//invalid serial_num
        }
        if(drone.getBattery() < 25){
            response.setMessage("Can not be loaded, low battery");
            return response; //can not be loaded, low battery
        }
        List<MedicationObj> new_medications = new ArrayList<>();
        if(request.getNew_medications() != null){
            new_medications = request.getNew_medications();
        }
        /*
        New medications are collection from the paylaod
         */
        for (int i = 0; i < new_medications.size(); i++) {
            Medication medication = new Medication();
            medication.setCode(new_medications.get(i).getCode());
            medication.setName(new_medications.get(i).getName());
            medication.setWeight(new_medications.get(i).getWeight());
            medication.setImage(new_medications.get(i).getImage());
            medications.add(medication);
            weight += new_medications.get(i).getWeight();
        }
        List<String> existing_medication = new ArrayList<>();
        if(request.getExisting_medication_codes()!=null){
            existing_medication = request.getExisting_medication_codes();
        }
        List<Medication> existing_medications = new ArrayList<>();
        for (int i= 0; i<existing_medication.size(); i++){
            Optional<Medication> medication = medicationRepository.findById(existing_medication.get(i));
            if(medication == null){
                response.setMessage("Invalid Code for medication : "+existing_medication.get(i));
                return response;//invalid code for Medication
            }
            weight += medication.get().getWeight();
            medications.add(medication.get());
        }

        if(weight > drone.getWeightLimit()){
            //can not load, the load exceeds the capacity of the drone
            response.setMessage("can not load, the load exceeds the capacity of the drone");
            return response;
        }

        Set<Medication> meds = drone.getMedications();
        Set<Medication> updated_med = new HashSet<Medication>(medications);
        meds.addAll(updated_med);
        drone.setMedications(meds);
        drone.setState(State.LOADING);
        droneRepository.save(drone);
        response.setSuccess(true);
        response.setMessage("Successfully loaded");

        return response;
    }

    @Override
    public List<Drone> checkAvailableDrones() {
        return droneRepository.findByState(State.IDLE);
    }
}
