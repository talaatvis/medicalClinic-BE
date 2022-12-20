package com.medicalclinic.app.services.appointments;

import com.medicalclinic.app.dtos.appointments.AppointmentDateRequest;
import com.medicalclinic.app.dtos.appointments.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    public AppointmentDto createAppointment(AppointmentDto appointmentDto);
    public List<AppointmentDto> fetchAppointmentsByDate(AppointmentDateRequest appointmentDateRequest);
    public List<AppointmentDto> fetchAppointmentsByPatientEmail(String patientEmail);
    public List<AppointmentDto> fetchAppointmentsByPhysicianEmail(String physicianEmail);

    public void deleteAppointmentById(Long id);
    public void deleteAllPatientAppointments(String patientEmail);


}
