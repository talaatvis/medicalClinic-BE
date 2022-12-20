package com.medicalclinic.app.dtos.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto implements Serializable {
    private Long appointmentId;
    private String patientEmail;
    private String physicianEmail;
    private String patientName;
    private String physicianName;
    @JsonIgnore
    private String date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Timestamp TargetDate;
    private String clinic = "INTERNAL_MEDICINE";
}
