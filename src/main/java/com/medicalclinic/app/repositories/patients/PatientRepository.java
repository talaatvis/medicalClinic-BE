package com.medicalclinic.app.repositories.patients;

import com.medicalclinic.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository  extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE p.email = ?1")
    public Patient findPatientByEmail(String email);
}
