package com.example.globalMedicare.service;

import java.util.Map;
import java.io.IOException;

import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.PatientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.io.IOException;


@Service
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepo patientRepo;

    public Map<String, Object> createPatient(Patient data) throws IOException {
        log.info("Creating new patient: {}",data.getPatientUsername());


        try {
            Patient patient = new Patient();
            patient.setPatientUsername(data.getPatientUsername());
            patient.setPatientPassword(data.getPatientPassword());
            System.out.println("getPatient_password :" + data.getPatientPassword());
            patient.setCreated_by(data.getCreated_by());
            patient.setCreated_at(data.getCreated_at());
            patient.setIssued_by(data.getIssued_by());
            patient.setIssued_at(data.getIssued_at());

            patientRepo.save(patient);
            log.info("Patient saved successfully: {}", patient);

            System.out.println("patient :" + patient.toString());

            System.out.println("patientRepo :" + patientRepo);

            return Map.of(
                    "patient", Map.of(
                            "name", patient.getPatientUsername(),
                            "created by", patient.getCreated_by(),
                            "created at", patient.getCreated_at(),
                            "Issued_by", patient.getIssued_by(),
                            "Issued_at", patient.getIssued_at())
            );
        } catch (Exception e) {
            log.error("Error creating patient: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create patient");
        }

    }
    public ResponseEntity<?> getPatient(@PathVariable int patientId) {

        log.info("Fetching patient with ID: {}", patientId);

        try {
            return patientRepo.findById(patientId)
                    .map(patient -> {
                        log.info("Patient found: {}", patient.getPatientUsername());

                        return ResponseEntity.ok(Map.of(
                                "patientID", patient.getPatientId(),
                                "patientUserName", patient.getPatientUsername(),
                                "created_by", patient.getCreated_by(),
                                "created_at", patient.getCreated_at(),
                                "issued_by", patient.getIssued_by(),
                                "issued_at", patient.getIssued_at()
                        ));
                    })
                    .orElseGet(() -> {
                        log.warn("Patient not found with ID: {}", patientId);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Map.of("error", "patient not found"));
                    });

        } catch (Exception e) {
            log.error("Error retrieving patient {}: {}", patientId, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch patient");
        }
    }


    public ResponseEntity<?> login(String patientUsername, String patientPassword) {
        log.info("Login attempt for username: {}", patientUsername);
        try {
            System.out.println("starting login");

            List<Patient> patients = patientRepo.findByPatientUsername(patientUsername);

            if (patients.isEmpty()) {
                log.warn("Login failed — username not found: {}", patientUsername);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid username or password"));
            }


            for (Patient p : patients) {
             if (p.getPatientPassword().equals(patientPassword)) {
                 log.info("Login successful for username: {}", patientUsername);
                     return ResponseEntity.ok(Map.of(
                             "patientId", p.getPatientId(),
                             "username", p.getPatientUsername()

             ));
         }
     }
            log.warn("Login failed — incorrect password for username: {}", patientUsername);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("error", "Invalid username or password"));
        } catch (Exception e) {
            log.error("Error during login for {}: {}", patientUsername, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}






