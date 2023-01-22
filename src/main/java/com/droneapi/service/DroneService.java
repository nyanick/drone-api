package com.droneapi.service;

import com.droneapi.model.Drone;
import com.droneapi.payload.DroneRegisterReponse;
import com.droneapi.payload.DroneRegisterRequest;
import com.droneapi.payload.LoadDroneRequest;
import com.droneapi.payload.LoadDroneResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DroneService {

    DroneRegisterReponse register(DroneRegisterRequest drone);

    Drone update(String serial_num, DroneRegisterRequest drone);

    Drone getDrone(String serial_num);

    List<Drone> getAllDrones();

    void removeDrone(String serial_num);

    LoadDroneResponse loadDrone(LoadDroneRequest request);

    List<Drone> checkAvailableDrones();



}
