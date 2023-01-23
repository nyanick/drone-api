package com.droneapi.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.droneapi.model.Drone;
import com.droneapi.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class Scheduler {

    @Autowired
    DroneRepository droneRepository;

    private static final Logger logger = LogManager.getLogger(Scheduler.class);

    /**
     * This method would be trigger every 2 minutes
     */
    @Scheduled(cron = "2 * * * * ?")
    public void CheckDroneBattery(){
        try {
            List<Drone> drones = droneRepository.findAll();
            logger.info("---------------------------------- Checking Drone battery level ----------------------------------");
            for (Drone drone: drones) {
                logger.info("Battery Serial Number : "+ drone.getSerialNumber() +"\tBattery Level : "+drone.getBattery() + " %");
            }
        }
        catch (Exception ex){
            logger.error("An error occurred in the check battery level cron method : "+ ex.getMessage());
        }

    }
}
