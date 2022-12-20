package com.medicalclinic.app.dtos.appointments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDateRequest implements Serializable {

    private String startDate;
    private String endDate;
}
