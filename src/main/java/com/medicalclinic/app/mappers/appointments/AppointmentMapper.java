package com.medicalclinic.app.mappers.appointments;

import com.medicalclinic.app.dtos.appointments.AppointmentDto;
import com.medicalclinic.app.entities.Appointment;
import com.medicalclinic.app.mappers.patients.PatientMapper;
import com.medicalclinic.app.mappers.physicians.PhysicianMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PhysicianMapper.class, PatientMapper.class})
public interface AppointmentMapper {

    @Mapping(target="appointmentId", source = "id")
    @Mapping(target="patientEmail", source = "patient.email")
    @Mapping(target="physicianEmail", source = "physician.email")
    @Mapping(target="patientName", source = "patient.name")
    @Mapping(target="physicianName", source = "physician.name")
    @Mapping(target="targetDate", source = "date", dateFormat ="yyyy.MM.dd HH.mm")
    public AppointmentDto fromEntityToDto(Appointment appointment);
    @Mapping(target="id", source = "appointmentId")
    @Mapping(target="patient.email", source = "patientEmail")
    @Mapping(target="physician.email", source = "physicianEmail")
    @Mapping(target="patient.name", source = "patientName")
    @Mapping(target="physician.name", source = "physicianName")
    @Mapping(target="date", source = "targetDate", dateFormat ="yyyy.MM.dd HH.mm")
    public Appointment fromDtoToEntity(AppointmentDto appointmentDto);

}
