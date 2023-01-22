package com.droneapi.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @NotNull
    @Max(value = 500, message = "The maximum weight limit is 500")
    private double weightLimit;
    @NotBlank
    @NotNull
    @Max(value = 100, message = "Invalid battery percentage value")
    private double battery;
    @NotBlank
    @NotNull
    private String state;

}
