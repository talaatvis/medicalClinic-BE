package com.medicalclinic.app.services.patients;

import com.medicalclinic.app.dtos.patients.PatientDto;
import com.medicalclinic.app.exceptions.BusinessException;
import com.medicalclinic.app.entities.Patient;
import com.medicalclinic.app.mappers.patients.PatientMapper;
import com.medicalclinic.app.repositories.patients.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient readyToSavePatient = patientMapper.fromDtoToEntity(patientDto);
        if(!patientExists(patientDto.getEmail())) {
            Patient createdPatient = patientRepository.save(readyToSavePatient);
            patientDto = patientMapper.fromEntityToDto(createdPatient);
        }else {
            throw new BusinessException("PATIENT_FOUND","Patient is Already Exist");
        }

        return patientDto;
    }

    @Override
    public void deletePatient(PatientDto patientDto) {
        Patient patientToBeDeleted = patientMapper.fromDtoToEntity(patientDto);
         patientRepository.delete(patientToBeDeleted);

    }

    @Override
    public List<PatientDto> fetchAllPatients() {
        List<Patient> allPatientsInDb = patientRepository.findAll();
        List<PatientDto> patientDtoList = Optional.of(allPatientsInDb.stream()
                .map(patientMapper::fromEntityToDto).collect(Collectors.toList()))
                .orElseThrow(()->{
                    return new BusinessException("NO_PATIENTS_FOUND", "No Patients Found");
                });

        return patientDtoList;
    }

    /**
     * check if the user was existing in DB or not
     * @param patientEmail
     * @return
     */
    private boolean patientExists(String patientEmail){
        PatientDto patientFound = fetchPatientByEmail(patientEmail);
        if(patientFound != null)
            return true;
        else
            return false;
    }

    @Override
    public PatientDto fetchPatientByEmail(String patientEmail) {
        Patient patient = patientRepository.findPatientByEmail(patientEmail);
        PatientDto mappedPatient = patientMapper.fromEntityToDto(patient);

        return mappedPatient;
    }
}
