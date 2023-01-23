package com.droneapi.repository;

import com.droneapi.model.Drone;
import com.droneapi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
    List<Drone> findByState(State idle);
}
