package com.example.globalMedicare.service;

import java.util.Map;
import java.io.IOException;

import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import java.util.List;


@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public Map<String, Object> createPatient(Patient data) throws IOException {

        Patient patient = new Patient();
        patient.setPatientUsername(data.getPatientUsername());
        patient.setPatientPassword(data.getPatientPassword());
        System.out.println("getPatient_password :"+data.getPatientPassword());
        patient.setCreated_by(data.getCreated_by());
        patient.setCreated_at(data.getCreated_at());
        patient.setIssued_by(data.getIssued_by());
        patient.setIssued_at(data.getIssued_at());

        patientRepo.save(patient);

        System.out.println("patient :"+patient.toString());

        System.out.println("patientRepo :"+patientRepo);

        return Map.of(
               "patient", Map.of("name", patient.getPatientUsername(),"created by",patient.getCreated_by(),"created at",patient.getCreated_at(),"Issued_by",patient.getIssued_by(),"Issued_at",patient.getIssued_at())
        );
    }
    public ResponseEntity<?> getPatient(@PathVariable int patientId) {
        System.out.println("starting getting patient");
        return patientRepo.findById(patientId)
                .map(patient -> ResponseEntity.ok(Map.of(
                        "patientID", patient.getPatientId(),
                        "patientUserName", patient.getPatientUsername(),
                        //"patientPassword", patient.getPatientPassword(),
                        "created_by", patient.getCreated_by(),
                        "created_at", patient.getCreated_at(),
                        "issued_by", patient.getIssued_by(),
                        "issued_at", patient.getIssued_at()

                )))
                
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "patient not found")));
                
    }

    public ResponseEntity<?> login(String patientUsername, String patientPassword) {
        System.out.println("starting login");

        List<Patient> patients = patientRepo.findByPatientUsername(patientUsername);

        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }
        

       for (Patient p : patients) {
        if (p.getPatientPassword().equals(patientPassword)) {
                return ResponseEntity.ok(Map.of(
                        "patientId", p.getPatientId(),
                        "username", p.getPatientUsername()
                
        ));
    }
}
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Map.of("error", "Invalid username or password")); }
}






