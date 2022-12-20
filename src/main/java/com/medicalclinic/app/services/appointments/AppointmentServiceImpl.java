package com.medicalclinic.app.services.appointments;

import com.medicalclinic.app.dtos.appointments.AppointmentDateRequest;
import com.medicalclinic.app.dtos.appointments.AppointmentDto;
import com.medicalclinic.app.entities.Appointment;
import com.medicalclinic.app.entities.Patient;
import com.medicalclinic.app.entities.Physician;
import com.medicalclinic.app.exceptions.BusinessException;
import com.medicalclinic.app.exceptions.Error;
import com.medicalclinic.app.mappers.appointments.AppointmentMapper;
import com.medicalclinic.app.repositories.appontments.AppointmentRepository;
import com.medicalclinic.app.repositories.patients.PatientRepository;
import com.medicalclinic.app.repositories.physicians.PhysicianRepository;
import com.medicalclinic.app.utilities.DateUtility;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    private final PhysicianRepository physicianRepository;

    private final DateUtility dateUtility;

    private final AppointmentMapper appointmentMapper;

    private final MessageSource messageSource;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        boolean existAppointment = isPatientHasAppointment(appointmentDto);
        if (existAppointment)
              throw new BusinessException (String.valueOf(Error.NativeCodeEnum.APPOINTMENT_FOUND.getValue()), messageSource.getMessage("medical.clinic.appointments.exist.appointment",
                null, LocaleContextHolder.getLocale()));

        Appointment toBeCreated = prepareTagetAppointment(appointmentDto);
        appointmentRepository.save(toBeCreated);

        return appointmentDto;
    }

    @Override
    public List<AppointmentDto> fetchAppointmentsByDate(AppointmentDateRequest appointmentDateRequest) {
        List<Appointment> dbAppointments = getListOfAppointmentsByDateRange(appointmentDateRequest);
        List<AppointmentDto> filteredAppointments = getMappedList(dbAppointments);
        return filteredAppointments;

    }
    private List<Appointment> getListOfAppointmentsByDateRange(AppointmentDateRequest appointmentDateRequest){
        Timestamp formattedStartDate = dateUtility.parseTimestamp(appointmentDateRequest.getStartDate());
        Timestamp formattedEndDate = dateUtility.parseTimestamp(appointmentDateRequest.getEndDate());
        List<Appointment> appointments = appointmentRepository.findAppointmentHistoryByDate(formattedStartDate, formattedEndDate);

    return appointments;
    }

    private List<AppointmentDto> getMappedList(List<Appointment> appointments){
        List<AppointmentDto> filteredAppointments = getMappedAppointments(appointments);

        return filteredAppointments;
    }

    @Override
    public List<AppointmentDto> fetchAppointmentsByPatientEmail(String patientEmail) {
        List<Appointment> filteredDBAppointments = getAppointmentsFromDb(patientEmail);
        List<AppointmentDto> filteredAppointmentList = getMappedList(filteredDBAppointments);

    return filteredAppointmentList;
    }

    private List <Appointment> getAppointmentsFromDb(String patientEmail){
        Patient patient = patientRepository.findPatientByEmail(patientEmail);
        if(patient == null)
            throw new BusinessException(String.valueOf(Error.NativeCodeEnum.PATIENT_NOT_FOUND.getValue()), messageSource.getMessage("medical.clinic.patient.notfound",
                            new String[]{patientEmail}, LocaleContextHolder.getLocale()));

        List<Appointment> filteredDBAppointments = appointmentRepository.findAppointmentByPatientEmail(patient.getId());
        return filteredDBAppointments;
    }

    @Override
    public List<AppointmentDto> fetchAppointmentsByPhysicianEmail(String physicianEmail) {
        Physician physician = getPhysicianByEmail(physicianEmail);
        List<Appointment> allDbAppointmentsByPhysician = appointmentRepository.findAppointmentByPhysician(physician.getId());
        List<AppointmentDto> mappedAppointments = getMappedAppointments(allDbAppointmentsByPhysician);

        return mappedAppointments;
    }

    private List<AppointmentDto> getMappedAppointments(List<Appointment> allDbAppointmentsByPhysician){
        List<AppointmentDto> mappedAppointments =  allDbAppointmentsByPhysician.stream()
                .map(appointment -> {return appointmentMapper.fromEntityToDto(appointment);}).collect(Collectors.toList());
        if(mappedAppointments.isEmpty())
            throw new BusinessException(String.valueOf(Error.NativeCodeEnum.NO_APPOINTMENTS_EXIST.getValue()), messageSource.getMessage("medical.clinic.appointments.nodata",
                    null, LocaleContextHolder.getLocale()));
        return mappedAppointments;

    }
    @Override
    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void deleteAllPatientAppointments(String patientEmail) {
        Patient patient = getPatient(patientEmail);
        appointmentRepository.deleteAllByPatient(patient.getId());
    }

    private boolean isPatientHasAppointment(AppointmentDto appointmentDto){
        Appointment existAppointment = prepareTagetAppointment(appointmentDto);
        Appointment targetAppointment = appointmentRepository.findAppointmentByDateTimeAndPatient(existAppointment.getDate(), existAppointment.getPatient().getId());
        if(targetAppointment != null)
            return true;
        else
            return false;

    }

    public Appointment prepareTagetAppointment(AppointmentDto appointmentDto){
        Timestamp targetDate = dateUtility.parseTimestamp(appointmentDto.getDate());
        appointmentDto.setTargetDate(targetDate);
        Appointment targetAppointment = appointmentMapper.fromDtoToEntity(appointmentDto);
        setPatientAndPhysician(appointmentDto, targetAppointment);

        return targetAppointment;
    }


    private void setPatientAndPhysician(AppointmentDto appointmentDto, Appointment targetAppointment){
        String patientEmail = Optional.ofNullable(appointmentDto.getPatientEmail()).orElseThrow(()->{
            return  new BusinessException("PATIENT_NOT_PROVIDED", "Please provide patient email");
        });
        Patient patient = getPatient(patientEmail);
        Physician physician = getPhysicianByEmail(appointmentDto.getPhysicianEmail());
        targetAppointment.setPhysician(physician);
        targetAppointment.setPatient(patient);
    }
    private Patient getPatient(String patientEmail){
        Patient patient = patientRepository.findPatientByEmail(patientEmail);
        if(patient == null)
            throw new BusinessException(String.valueOf(Error.NativeCodeEnum.PATIENT_NOT_FOUND.getValue()), messageSource.getMessage("medical.clinic.patient.notfound",
                    new String[]{patientEmail}, LocaleContextHolder.getLocale()));
        return patient;

    }

    private Physician getPhysicianByEmail(String email){
        Physician physician = physicianRepository.findPhysicianByEmail(email);
        if(physician == null)
            throw new BusinessException(String.valueOf(Error.NativeCodeEnum.PHYSICIAN_NOT_FOUND.getValue()), messageSource.getMessage("medical.clinic.physician.notfound",
                    new String[]{email}, LocaleContextHolder.getLocale()));
        return physician;
    }



}
