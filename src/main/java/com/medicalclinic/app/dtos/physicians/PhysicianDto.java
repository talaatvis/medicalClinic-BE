package com.medicalclinic.app.dtos.physicians;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysicianDto implements Serializable {
    private String name;
    private String contactNumber;

    private String email;
}
