package com.example.globalMedicare.controller;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.service.DoctorService;

import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/doctors")

public class DoctorController {


    @Autowired
    private DoctorService doctorService;

    @GetMapping("/types") 
    public Set<String> getDoctorTypes() { 
        System.out.println("type cont:"+doctorService.getAllTypes());
        return doctorService.getAllTypes(); 
    }

    @GetMapping("/type/{type}") 
    public List<Doctor> getDoctorsByType(@PathVariable String type) { 
        System.out.println("getDoctorsByType invoked .. type :"+ type);
        return doctorService.getDoctorsByType(type); 
    }
    

}