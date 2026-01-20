package com.example.globalMedicare.controller;
import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.Map;
import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/patientDetails")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patients")
    public ResponseEntity<?> createPatient(@RequestBody Patient data) {
        System.out.println("post mapping starting");


        try {

            Map<String, Object> result = patientService.createPatient(data);
            System.out.println("result: " + result);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Patient not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatient(@PathVariable int patientId) {
        
        return patientService.getPatient(patientId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {
        String patientUsername = data.get("username");
        String patientPassword = data.get("password");

        return patientService.login(patientUsername, patientPassword);
    }

}
