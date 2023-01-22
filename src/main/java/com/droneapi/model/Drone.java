package com.droneapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


enum Model {Lightweight, Middleweight, Cruiserweight, Heavyweight}
enum State {IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING}

@Entity
@Table(name="drones")
@Getter
@Setter
@NoArgsConstructor
public class Drone {

    @Id
    @Column(name = "serial_num", columnDefinition = "VARCHAR(100) NOT NULL")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit")
    @Max(500)
    private double weightLimit;

    @Column(name="battery", columnDefinition="Decimal(10,2) default '100.00'")
    @Max(100)
    private double battery;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Medication> medications;

}

