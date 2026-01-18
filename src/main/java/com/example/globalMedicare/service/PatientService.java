package com.example.globalMedicare.service;

import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;


import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;


@Service
public class PatientService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    public Map<String, Object> createPatient(Patient data) throws IOException {
        Patient patient = new Patient();
        patient.setPatient_username(data.getPatient_username());
        patient.setPatient_password(data.getPatient_password());
        System.out.println("getPatient_password :"+data.getPatient_password());
        patient.setCreated_by(data.getCreated_by());
        patient.setCreated_at(data.getCreated_at());
        patient.setIssued_by(data.getIssued_by());
        patient.setIssued_at(data.getIssued_at());

        appointmentRepo.save(patient);

        System.out.println("patient :"+patient.toString());

        System.out.println("appointmentRepo :"+appointmentRepo);

        return Map.of(
               "patient", Map.of("name", patient.getPatient_username(),"password",patient.getPatient_password(),"created by",patient.getCreated_by(),"created at",patient.getCreated_at(),"Issued_by",patient.getIssued_by(),"Issued_at",patient.getIssued_at())
        );
    }
    public ResponseEntity<?> getPatient(@PathVariable int patientId) {
        System.out.println("starting getting patient");
        return appointmentRepo.findById(patientId)
                .map(patient -> ResponseEntity.ok(Map.of(
                        "patientID", patient.getPatientId(),
                        "patient_userName", patient.getPatient_username(),
                        "patient_password", patient.getPatient_password(),
                        "created_by", patient.getCreated_by(),
                        "created_at", patient.getCreated_at(),
                        "issued_by", patient.getIssued_by(),
                        "issued_at", patient.getIssued_at()

                )))
                
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "patient not found")));
                
    }

}
