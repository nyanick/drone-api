package com.droneapi;

import com.droneapi.model.Drone;
import com.droneapi.model.Medication;
import com.droneapi.model.Model;
import com.droneapi.model.State;
import com.droneapi.payload.LoadDroneRequest;
import com.droneapi.payload.MedicationObj;
import com.droneapi.repository.DroneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class DroneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init (DroneRepository droneRepository){
		return args -> {
			/*
			we would create a drone, and medications here
			 */
			Drone drone = new Drone();
			drone.setSerialNumber("SNR1");
			drone.setBattery(100.0);
			drone.setState(State.IDLE);
			drone.setModel(Model.Heavyweight);
			drone.setWeightLimit(400);
			droneRepository.save(drone);

			Drone drone2 = new Drone();
			drone2.setSerialNumber("SNR2");
			drone2.setBattery(80.0);
			drone2.setState(State.RETURNING);
			drone2.setModel(Model.Heavyweight);
			drone2.setWeightLimit(300);
			droneRepository.save(drone2);

			Drone drone3 = new Drone();
			drone3.setSerialNumber("SNR3");
			drone3.setBattery(80.0);
			drone3.setState(State.LOADING);
			drone3.setModel(Model.Heavyweight);
			drone3.setWeightLimit(300);
			/*
			we would load some medications in Drone3 : SN3
			/*
			We create new medications
			 */
			List<Medication> new_meds = new ArrayList<>();
			Medication med1 = new Medication();
			med1.setName("Paracetamol");
			med1.setWeight(200);
			med1.setCode("CDN2");
			med1.setImage("paracetamol.png");
			new_meds.add(med1);
			drone3.setMedications(new HashSet<>(new_meds));
			droneRepository.save(drone3);


		};
	}

}
