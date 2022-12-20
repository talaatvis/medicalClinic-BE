package com.medicalclinic.app.services.physicians;

import com.medicalclinic.app.dtos.physicians.PhysicianDto;
import com.medicalclinic.app.entities.Physician;
import com.medicalclinic.app.exceptions.BusinessException;
import com.medicalclinic.app.mappers.physicians.PhysicianMapper;
import com.medicalclinic.app.repositories.physicians.PhysicianRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhysicianServiceImpl implements PhysicianService {

    private PhysicianRepository physicianRepository;
    private PhysicianMapper physicianMapper;
    @Override
    public PhysicianDto createPhysician(PhysicianDto physicianDto) {
        Physician physician = physicianMapper.fromDtoToEntity(physicianDto);
        if(!physicianExists(physicianDto.getEmail())) {
            Physician createdPatient = physicianRepository.save(physician);
            physicianDto = physicianMapper.fromEntityToDto(createdPatient);
        }else {
            throw new BusinessException("PHYSICIAN_FOUND","Physician is Already Exist");
        }

        return physicianDto;
    }

    @Override
    public List<PhysicianDto> fetchAllPhysicians() {
        List<Physician> allPhysicianFromDb = physicianRepository.findAll();
        List<PhysicianDto> physicianDtos = allPhysicianFromDb.stream()
                        .map(physicianMapper::fromEntityToDto).collect(Collectors.toList());


        if(physicianDtos.isEmpty())
            throw new BusinessException("NO_PHYSICIANS_FOUND", "No Physicians Found");

        return physicianDtos;
    }

    private boolean physicianExists(String physicianEmail){
        PhysicianDto physicianFound = fetchPhysicianByEmail(physicianEmail);
        if(physicianFound != null)
            return true;
        else
            return false;
    }

    @Override
    public PhysicianDto fetchPhysicianByEmail(String physicianEmail) {
        Physician physician = physicianRepository.findPhysicianByEmail(physicianEmail);
        PhysicianDto mappedPhysician = physicianMapper.fromEntityToDto(physician);
        return mappedPhysician;
    }


}
