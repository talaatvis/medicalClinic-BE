package com.medicalclinic.app.controllers.physicians;

import com.medicalclinic.app.dtos.physicians.PhysicianDto;
import com.medicalclinic.app.services.physicians.PhysicianService;
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
@RequestMapping("/api/v1/physicians")
public class PhysicianController {

    private final PhysicianService physicianService;


    @ApiOperation(value = "Add Physician", notes = "Must provide Physician Details",
            response = PhysicianDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Physician Created", response = PhysicianDto.class)
    })
    @PostMapping
    public ResponseEntity<PhysicianDto> addPhysician(@RequestBody PhysicianDto physicianDto){
        physicianDto = physicianService.createPhysician(physicianDto);

        return new ResponseEntity<>(physicianDto, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Retrieve All Physicians in the DB",
            responseContainer = "List",
            response = PhysicianDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Physicians Retrieved", response = PhysicianDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PhysicianDto>> getAllPhysicians(){
        List<PhysicianDto> allPhysicians = physicianService.fetchAllPhysicians();

        return new ResponseEntity<>(allPhysicians, HttpStatus.OK);
    }

}
