package com.example.outpatient.appointment.system.Controller;

import com.example.outpatient.appointment.system.Entity.Patient;
import com.example.outpatient.appointment.system.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/patient_api")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @PostMapping(path = "add")
    public String addPatient(@RequestBody Patient patient){
        patientRepository.save(patient);
        return "New patient added successfully";
    }

}
