package com.medicalclinic.app.dtos.patients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto implements Serializable {

    private String name;
    private String contactNumber;
    private String address;

    private String email;
}
