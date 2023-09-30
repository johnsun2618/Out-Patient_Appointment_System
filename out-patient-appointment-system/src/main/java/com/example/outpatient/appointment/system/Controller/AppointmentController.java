package com.example.outpatient.appointment.system.Controller;

import com.example.outpatient.appointment.system.Entity.Appointment;
import com.example.outpatient.appointment.system.Entity.AppointmentBooking;
import com.example.outpatient.appointment.system.Entity.Doctor;
import com.example.outpatient.appointment.system.Entity.Patient;
import com.example.outpatient.appointment.system.Repository.AppointmentRepository;
import com.example.outpatient.appointment.system.Repository.DoctorRepository;
import com.example.outpatient.appointment.system.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/appointment_api")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping(path = "/add")
    public String bookAppointment(@RequestBody AppointmentBooking appointmentBooking){

        //validate appointment booking
        if (appointmentBooking.getDoctorId() == null || appointmentBooking.getPatientId() == null ||
                appointmentBooking.getAppointmentDateTime() == null) {
            return "Invalid appointment request";
        }

        //check weather doctor exists or not
        Doctor doctor = doctorRepository.findById(appointmentBooking.getDoctorId()).orElse(null);
        if (doctor == null) {
            return "Doctor not found";
        }

        //check weather patient exists or not
        Patient patient = patientRepository.findById(appointmentBooking.getPatientId()).orElse(null);
        if (patient == null) {
            return "Patient not found";
        }

        //check weather doctor has slots available or not
        List<Appointment> appointments = appointmentRepository
                .findByDoctorAndAppointmentDateTimeBetween(doctor,
                        appointmentBooking.getAppointmentDateTime().withHour(0).withMinute(0).withSecond(0),
                        appointmentBooking.getAppointmentDateTime().withHour(23).withMinute(59).withSecond(59));

        if (appointments.size() >= doctor.getPatientsPerDay()) {
            return "Doctor is fully booked for the day";
        }

        //if everything seems perfect then create and save the appointment
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDateTime(appointmentBooking.getAppointmentDateTime());

        appointmentRepository.save(appointment);

        return "Appointment booked successfully";



    }

}
