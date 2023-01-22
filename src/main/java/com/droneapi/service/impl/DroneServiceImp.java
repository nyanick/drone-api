package com.droneapi.service.impl;

import com.droneapi.model.Drone;
import com.droneapi.payload.DroneRegisterReponse;
import com.droneapi.payload.DroneRegisterRequest;
import com.droneapi.payload.LoadDroneRequest;
import com.droneapi.payload.LoadDroneResponse;
import com.droneapi.service.DroneService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DroneServiceImp implements DroneService {

    @Override
    public DroneRegisterReponse register(DroneRegisterRequest drone) {
        return null;
    }

    @Override
    public Drone update(String serial_num, DroneRegisterRequest drone) {
        return null;
    }

    @Override
    public Drone getDrone(String serial_num) {
        return null;
    }

    @Override
    public List<Drone> getAllDrones() {
        return null;
    }

    @Override
    public void removeDrone(String serial_num) {

    }

    @Override
    public LoadDroneResponse loadDrone(LoadDroneRequest request) {
        return null;
    }

    @Override
    public List<Drone> checkAvailableDrones() {
        return null;
    }
}
