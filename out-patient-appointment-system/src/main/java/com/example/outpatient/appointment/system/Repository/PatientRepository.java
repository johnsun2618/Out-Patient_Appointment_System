package com.example.outpatient.appointment.system.Repository;

import com.example.outpatient.appointment.system.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
