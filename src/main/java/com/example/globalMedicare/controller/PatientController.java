package com.example.globalMedicare.controller;
import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.Map;
import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/patientDetails")


public class PatientController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<?> createPatient(@RequestBody Patient data) {
        log.info("Received request to create patient: {}", data.getPatientUsername());
        System.out.println("post mapping starting");


        try {

            Map<String, Object> result = patientService.createPatient(data);
            System.out.println("result: " + result);
            log.info("Patient created successfully: {}", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IOException e) {
            log.error("IO error while creating patient: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Patient not found"));
        } catch (Exception e) {
            log.error("Unexpected error while creating patient: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatient(@PathVariable int patientId) {
        log.info("Fetching patient with ID: {}", patientId);

        try {
            ResponseEntity<?> response = patientService.getPatient(patientId);
            log.info("Patient fetch successful for ID: {}", patientId);
            return  response;

        } catch (Exception e) {
            log.error("Error fetching patient with ID {}: {}", patientId, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {

            String patientUsername = data.get("username");
            String patientPassword = data.get("password");
            log.info("Login attempt for username: {}", patientUsername);
        try {
            ResponseEntity<?> response = patientService.login(patientUsername, patientPassword);
            log.info("Login successful for username: {}", patientUsername);
            return response;


        } catch (Exception e) {
            log.error("Login failed for username {}: {}", patientUsername, e.getMessage(), e);
            throw new RuntimeException("Login failed");
        }
    }

}
