package com.medicalclinic.app.repositories.physicians;

import com.medicalclinic.app.entities.Patient;
import com.medicalclinic.app.entities.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    @Query("SELECT p FROM Physician p WHERE p.email = ?1")
    public Physician findPhysicianByEmail(String email);
}
