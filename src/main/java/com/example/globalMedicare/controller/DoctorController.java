package com.example.globalMedicare.controller;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.service.DoctorService;

import java.util.List;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/doctors")

public class DoctorController {


    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/types") 
    public Set<String> getDoctorTypes() {
        log.info("Fetching all doctor types");
        try {
            Set<String> types = doctorService.getAllTypes();
            System.out.println("type cont:"+types);
            log.info("Doctor types retrieved: {}", types);
            return types;

        } catch (Exception e) {
            log.error("Error fetching doctor types: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/type/{type}") 
    public List<Doctor> getDoctorsByType(@PathVariable String type) {
        log.info("Fetching doctors for type: {}", type);
        try {
            List<Doctor> doctors = doctorService.getDoctorsByType(type);
            log.info("Doctors retrieved for type {}: {}", type, doctors);
            return doctors;
        } catch (Exception e) {
            log.error("Error fetching doctors for type {}: {}", type, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch doctors by type");
        }
    }
    

}