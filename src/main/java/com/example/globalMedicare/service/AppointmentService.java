package com.example.globalMedicare.service;
import java.sql.Date;



import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.AppointmentRepo;
import com.example.globalMedicare.repository.DoctorRepo;
import com.example.globalMedicare.repository.PatientRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AppointmentService {


    private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepo appointmentRepo;

     @Autowired 
    private PatientRepo patientRepo;

     @Autowired 
    private DoctorRepo doctorRepo;


    public AppointmentDetail makeAppointment(int doctorId, int patientId,String location, Date date ){
        log.info("Creating appointment: doctorId={}, patientId={}, location={}, date={}", doctorId, patientId, location, date);

        try {

            Patient patient = patientRepo.findById(patientId)
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            Doctor doctor = doctorRepo.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));



            AppointmentDetail appointment=new AppointmentDetail();
            appointment.setPatient(patient);
            appointment.getDoctors().add(doctor);
            appointment.setAppointment_date(date);
            appointment.setAppointment_location(location);

            AppointmentDetail saved = appointmentRepo.save(appointment);
            log.info("Appointment created successfully with ID: {}", saved.getAppointmentId());
            return saved;


        } catch (Exception e) {
            log.error("Error creating appointment for doctorId={} and patientId={}: {}", doctorId, patientId, e.getMessage(), e);

            throw new RuntimeException("Failed to create appointment");
        }
    }

}
