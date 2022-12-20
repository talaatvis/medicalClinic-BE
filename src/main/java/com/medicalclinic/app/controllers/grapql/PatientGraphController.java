package com.medicalclinic.app.controllers.grapql;

import com.medicalclinic.app.dtos.patients.PatientDto;
import com.medicalclinic.app.services.patients.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PatientGraphController {

    private final PatientService patientService;
    @QueryMapping
    public PatientDto patientByEmail(@Argument String email){
        return patientService.fetchPatientByEmail(email);
    }
}
