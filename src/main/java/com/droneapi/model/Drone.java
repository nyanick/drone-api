package com.droneapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @DecimalMax("500.0") @DecimalMin("0.0")
    private double weightLimit;

    @Column(name="battery", columnDefinition="Decimal(10,2) default '100.00'")
    @NotNull
    @DecimalMax("100.0") @DecimalMin("0.0")
    private double battery;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "drone_med",joinColumns = {@JoinColumn(name = "drone_id", referencedColumnName = "serial_num")},inverseJoinColumns = {@JoinColumn(name = "med_id", referencedColumnName = "code")})
    private Set<Medication> medications = new HashSet<>();


}

