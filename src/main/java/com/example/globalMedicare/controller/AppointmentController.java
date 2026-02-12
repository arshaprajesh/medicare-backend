package com.example.globalMedicare.controller;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.service.AppointmentService;

import java.sql.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/appointment")


public class AppointmentController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointmentDetails")
    public AppointmentDetail makeAppointment(@RequestParam int doctorId, @RequestParam int patientId, @RequestParam String location,@RequestParam Date date){
        log.info("Received appointment request: doctorId={}, patientId={}, location={}, date={}", doctorId, patientId, location, date);
        try {
            AppointmentDetail appointment = appointmentService.makeAppointment(doctorId,patientId,location,date);
            log.info("Appointment created successfully: {}", appointment);
            return appointment;

        } catch (Exception e) {
            log.error("Error while creating appointment for doctorId={} and patientId={}: {}", doctorId, patientId, e.getMessage(), e);
            throw new RuntimeException("Failed to create appointment");
        }




    }



}
