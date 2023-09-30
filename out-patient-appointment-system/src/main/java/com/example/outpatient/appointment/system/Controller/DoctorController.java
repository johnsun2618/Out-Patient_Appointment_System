package com.example.outpatient.appointment.system.Controller;

import com.example.outpatient.appointment.system.Entity.Doctor;
import com.example.outpatient.appointment.system.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping(path = "/doctor_api")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping(path = "/add")
    public String addNewDoctor(@RequestBody Doctor doctor){
        doctorRepository.save(doctor);
        return "Doctor Added Successfully";
    }

    @GetMapping(path = "/fetchAll")
    public List<Doctor> fetchAllDoctor(){
       return doctorRepository.findAll();
    }

    @GetMapping(path = "/fetchById/{id}")
    public Doctor fetchById(@PathVariable Long id){
        return doctorRepository.findById(id).orElse(null);
    }

}
