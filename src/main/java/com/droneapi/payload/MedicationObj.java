package com.droneapi.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicationObj {
    @NotBlank
    @NotNull
    @Size(min = 2, message = "Invalid code, too short")
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid Code, does not respect contraints")
    String code;
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[A-z0-9_]+$", message = "Name should not contain special characters")
    String name;
    @NotBlank
    @NotNull
    double weight;
    String image;
}
