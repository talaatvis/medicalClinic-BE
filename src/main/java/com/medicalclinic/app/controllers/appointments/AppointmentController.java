package com.medicalclinic.app.controllers.appointments;

import com.medicalclinic.app.dtos.appointments.AppointmentDateRequest;
import com.medicalclinic.app.dtos.appointments.AppointmentDto;
import com.medicalclinic.app.services.appointments.AppointmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/appointments/")
public class AppointmentController {

    private final AppointmentService appointmentService;


    @ApiOperation(value = "Add Appointment", notes = "Must provide Appointment Details",
            response = AppointmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Appointment Created", response = AppointmentDto.class)
    })
    @PostMapping
    public ResponseEntity<AppointmentDto> addAppointment(@RequestBody AppointmentDto appointmentRequest){
        AppointmentDto appointmentDto = appointmentService.createAppointment(appointmentRequest);

        return new ResponseEntity<>(appointmentDto, HttpStatus.CONFLICT);

    }



    @ApiOperation(value = "Filter Appointments By Date Past/Future", notes = "Must provide start date and end date",
            responseContainer = "List",
            response = AppointmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search Results Provided", response = AppointmentDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<List<AppointmentDto>> searchAppointmentsByDate(@RequestBody AppointmentDateRequest appointmentDateRequest ){
        List<AppointmentDto> filteredAppointments = appointmentService.fetchAppointmentsByDate(appointmentDateRequest);

        return   ResponseEntity.ok(filteredAppointments);

    }


    @ApiOperation(value = "Filter Appointments By Patient Email", notes = "Must provide Patient Email",
            responseContainer = "List",
            response = AppointmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search Results Provided", response = AppointmentDto.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/patients/{patientEmail}")
    public ResponseEntity<List<AppointmentDto>> searchAppointmentsByDate(@PathVariable String patientEmail, @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lang){
        LocaleContextHolder.setLocale(new Locale(lang));
        List<AppointmentDto> filteredAppointments = appointmentService.fetchAppointmentsByPatientEmail(patientEmail);

        return   new ResponseEntity<>(filteredAppointments, HttpStatus.OK);

    }
}
