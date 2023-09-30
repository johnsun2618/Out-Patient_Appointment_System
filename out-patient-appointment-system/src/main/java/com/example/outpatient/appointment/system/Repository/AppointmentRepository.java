package com.example.outpatient.appointment.system.Repository;

import com.example.outpatient.appointment.system.Entity.Appointment;
import com.example.outpatient.appointment.system.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentDateTimeBetween(Doctor doctor,
                                                                LocalDateTime localDateTime,
                                                                LocalDateTime localDateTime1);
}
