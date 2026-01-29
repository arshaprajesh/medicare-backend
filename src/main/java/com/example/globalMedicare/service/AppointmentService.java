package com.example.globalMedicare.service;
import java.sql.Date;



import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.AppointmentRepo;
import com.example.globalMedicare.repository.DoctorRepo;
import com.example.globalMedicare.repository.PatientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AppointmentService {


    @Autowired 
    private AppointmentRepo appointmentRepo;

     @Autowired 
    private PatientRepo patientRepo;

     @Autowired 
    private DoctorRepo doctorRepo;


    public AppointmentDetail makeAppointment(int doctorId, int patientId,String location, Date date ){
        
        AppointmentDetail appointment=new AppointmentDetail();



        Patient patient = patientRepo.getReferenceById(patientId); 
        Doctor doctor = doctorRepo.getReferenceById(doctorId);

        appointment.setPatient(patient); 
        appointment.getDoctors().add(doctor);

        appointment.setAppointment_date(date); 
        appointment.setAppointment_location(location);

        /*AppointmentDetail appointmentDetailVal = appointmentRepo.save(appointment);

        return appointmentDetailVal;*/

        return appointmentRepo.save(appointment);
    }

}
