package com.medicalclinic.app.mappers.patients;


import com.medicalclinic.app.dtos.patients.PatientDto;
import com.medicalclinic.app.entities.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
     PatientDto fromEntityToDto(Patient patient) ;
     Patient fromDtoToEntity(PatientDto patientDto);


}
