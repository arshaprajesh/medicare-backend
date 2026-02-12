package com.example.globalMedicare.service;

import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.PatientRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepo patientRepo;

    @InjectMocks
    private PatientService patientService;

    @Test
    void testCreatePatient() throws Exception {
        // Arrange
        Patient p = new Patient();
        p.setPatientUsername("john");
        p.setPatientPassword("123");
        p.setCreated_by("admin");
        p.setCreated_at(new Date());
        p.setIssued_by("system");
        p.setIssued_at(new Date());


        Mockito.when(patientRepo.save(any(Patient.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Map<String, Object> result = patientService.createPatient(p);

        // Extract nested map
        Map<String, Object> patientMap = (Map<String, Object>) result.get("patient");

        // Assert
        assertEquals("john", patientMap.get("name"));
    }
}
