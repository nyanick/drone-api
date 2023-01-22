package com.droneapi.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterMedicationRequest {
    @NotBlank
    @NotNull
    @Size(min = 2, message = "Invalid code, too short")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$" , message = "Invalid Code, does not respect contraints")
    private String code;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[A-z0-9_]+$" , message = "Name should not contain special characters")
    private String name;
    @NotBlank
    @NotNull
    private double weight;

    private String image;
}
