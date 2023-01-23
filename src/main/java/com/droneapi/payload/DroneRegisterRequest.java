package com.droneapi.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DroneRegisterRequest {

    @NotBlank
    @NotNull
    @Size(min = 2, message = "Invalid Serial Number, too short")
    @Size(max = 100, message = "Max length is 100")
    private String serialNumber;
    @NotBlank
    @NotNull
    private String model;
    @NotNull
    @DecimalMax("500.0") @DecimalMin("0.0")
    private double weightLimit;
    @NotNull
    @DecimalMax("100.0") @DecimalMin("0.0")
    private double battery;
    @NotBlank
    @NotNull
    private String state;

}
