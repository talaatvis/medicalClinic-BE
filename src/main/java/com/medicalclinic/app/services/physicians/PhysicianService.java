package com.medicalclinic.app.services.physicians;

import com.medicalclinic.app.dtos.physicians.PhysicianDto;

import java.util.List;

public interface PhysicianService {
    public PhysicianDto createPhysician (PhysicianDto physicianDto);
    public List<PhysicianDto> fetchAllPhysicians();

    public PhysicianDto fetchPhysicianByEmail(String physicianEmail);
}
