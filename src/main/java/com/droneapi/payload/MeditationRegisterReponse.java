package com.droneapi.payload;

import com.droneapi.model.Drone;
import com.droneapi.model.Medication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MeditationRegisterReponse {

    private boolean success;
    private String message;
    private Medication data;
    private LocalDateTime timestamp;


}
