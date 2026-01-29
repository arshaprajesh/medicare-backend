package com.example.globalMedicare.controller;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.service.AppointmentService;

import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/appointment")


public class AppointmentController {

@Autowired
private AppointmentService appointmentService;

@PostMapping("/appointmentDetails")

public AppointmentDetail makeAppointment(@RequestParam int doctorId, @RequestParam int patientId, @RequestParam String location,@RequestParam Date date){
    return appointmentService.makeAppointment(doctorId,patientId,location,date);


}



}
