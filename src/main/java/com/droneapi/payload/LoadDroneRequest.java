package com.droneapi.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoadDroneRequest {
    @NotBlank
    @NotNull
    private String drone_serial_num;

    /**
     * There are two possibilty to load medications, medications that already exist in the system
     * Or we load new medications whereby we would register them in the system and load them to the drone
     * If this files is filled then we would consider already register meds
     */
    private List<String> existing_medication_codes;

    private List<MedicationRegisterRequest> new_medications;

    public List<MedicationObj> new_medications_to_be_added;
}


