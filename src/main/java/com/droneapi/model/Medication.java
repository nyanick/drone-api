package com.droneapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="medications")
@Getter
@Setter
@NoArgsConstructor

public class Medication {
    @Id
    @Column(name = "code", columnDefinition = "VARCHAR(255) NOT NULL")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$" , message = "Invalid Code, does not respect contraints")
    private String code;

    @NotNull
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[A-z0-9_]+$" , message = "Name should not contain special characters")
    private String name;

    @NotNull
    private double weight;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drone_serial_num", nullable = false)
    private Drone drone;

}
