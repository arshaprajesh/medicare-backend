

package com.example.globalMedicare.controller;

import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.service.PatientService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    void testCreatePatientSuccess() throws Exception {
        Map<String, Object> mockResponse = Map.of(
                "patient", Map.of("name", "john")
        );

        Mockito.when(patientService.createPatient(any(Patient.class)))
                .thenReturn(mockResponse);

        String json = """
                {
                  "patientUsername": "john",
                  "patientPassword": "123"
                }
                """;

        mockMvc.perform(post("/patientDetails/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.patient.name").value("john"));
    }

    @Test
    void testCreatePatientIOException() throws Exception {
        Mockito.when(patientService.createPatient(any(Patient.class)))
                .thenThrow(new IOException("DB error"));

        String json = """
                {
                  "patientUsername": "john",
                  "patientPassword": "123"
                }
                """;

        mockMvc.perform(post("/patientDetails/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Patient not found"));
    }

    @Test
    void testCreatePatientGenericException() throws Exception {
        Mockito.when(patientService.createPatient(any(Patient.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        String json = """
                {
                  "patientUsername": "john",
                  "patientPassword": "123"
                }
                """;

        mockMvc.perform(post("/patientDetails/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Unexpected error"));
    }

    @Test
    void testGetPatient() throws Exception {
        ResponseEntity<?> mockResponse = ResponseEntity.ok(
                Map.of("patientID", 1, "patientUserName", "john")
        );

        Mockito.<ResponseEntity<?>>when(patientService.getPatient(1))
                .thenReturn((ResponseEntity<?>)mockResponse);

        mockMvc.perform(get("/patientDetails/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientID").value(1))
                .andExpect(jsonPath("$.patientUserName").value("john"));
    }

    @Test
    void testLoginSuccess() throws Exception {
        ResponseEntity<?> mockResponse = ResponseEntity.ok(
                Map.of("patientId", 1, "username", "john")
        );

        Mockito.<ResponseEntity<?>>when(patientService.login(eq("john"), eq("123")))
                .thenReturn((ResponseEntity<?>) mockResponse);

        String json = """
                {
                  "username": "john",
                  "password": "123"
                }
                """;

        mockMvc.perform(post("/patientDetails/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    void testLoginFailure() throws Exception {
        /*ResponseEntity<?> mockResponse = ResponseEntity.status(401)
                .body(Map.of("error", "Invalid username or password"));*/

        Mockito.<ResponseEntity<?>>when(patientService.login(eq("john"), eq("wrong")))
                .thenReturn(ResponseEntity.status(401)
                        .body(Map.of("error", "Invalid username or password")));




        String json = """
                {
                  "username": "john",
                  "password": "wrong"
                }
                """;

        mockMvc.perform(post("/patientDetails/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid username or password"));
    }
}
