package com.medicalclinic.app.controllers.patient;

import com.medicalclinic.app.dtos.patients.PatientDto;
import com.medicalclinic.app.services.patients.PatientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;


    @ApiOperation(value = "Add Patient", notes = "Must provide Patient Details",
            response = PatientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Patient Created", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto){
        PatientDto savedPatient = patientService.createPatient(patientDto);

        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve All Patients in the DB",
            responseContainer = "List",
            response = PatientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Patients Retrieved", response = PatientDto.class)
    })
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<PatientDto> allPatients = patientService.fetchAllPatients();

        return new ResponseEntity<>(allPatients, HttpStatus.OK);
    }

}
