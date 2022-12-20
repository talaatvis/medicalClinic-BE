package com.medicalclinic.app.services.patients;

import com.medicalclinic.app.dtos.patients.PatientDto;

import java.util.List;

public interface PatientService {
    public PatientDto createPatient(PatientDto patientDto);
    public void deletePatient(PatientDto patientDto);
    public List<PatientDto> fetchAllPatients();
    public PatientDto fetchPatientByEmail(String patientEmail);

}
