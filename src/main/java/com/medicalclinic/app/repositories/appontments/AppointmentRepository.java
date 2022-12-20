package com.medicalclinic.app.repositories.appontments;

import com.medicalclinic.app.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.patient.id = ?1")
    public List<Appointment> findAppointmentByPatientEmail(Long patientId);
    @Query("SELECT a FROM Appointment a WHERE a.physician.id = ?1")
    public List<Appointment> findAppointmentByPhysician(Long physicianId);
    @Query("SELECT a FROM Appointment a WHERE a.date between ?1  and ?2")
    public List<Appointment> findAppointmentHistoryByDate(Timestamp startDate, Timestamp endDate);
    @Query("SELECT a FROM Appointment a WHERE a.date = ?1  and a.patient.id = ?2")
    public Appointment findAppointmentByDateTimeAndPatient(Timestamp date, Long patientId);
    @Query("DELETE FROM Appointment a WHERE a.patient.id = ?1")
    public void deleteAllByPatient(Long patientId);


}
