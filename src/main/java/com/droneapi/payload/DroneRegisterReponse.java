package com.droneapi.payload;

import com.droneapi.model.Drone;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DroneRegisterReponse {

    private boolean success;
    private String message;
    private Drone data;
    private LocalDateTime timestamp;


}
