package com.droneapi.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoadDroneResponse {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
}
