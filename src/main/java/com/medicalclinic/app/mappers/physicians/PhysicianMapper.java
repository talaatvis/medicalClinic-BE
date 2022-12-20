package com.medicalclinic.app.mappers.physicians;

import com.medicalclinic.app.dtos.physicians.PhysicianDto;
import com.medicalclinic.app.entities.Physician;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhysicianMapper {
    PhysicianDto fromEntityToDto(Physician physician);
    Physician fromDtoToEntity(PhysicianDto physicianDto);
}
