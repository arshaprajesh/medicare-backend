package com.example.globalMedicare.service;

import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.repository.DoctorRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepo doctorRepo;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor d1;
    private Doctor d2;
    private Doctor d3;

    @BeforeEach
    void setup() {
        // Clear static cache before each test
        DoctorService.doctorcache.clear();

        d1 = new Doctor();
        d1.setDoctorName("Dr. John");
        d1.setType("Cardiology");
        d1.setLocation("NYC");
        d1.setDate(Date.valueOf("2026-02-01"));
        d1.setFee(200);

        d2 = new Doctor();
        d2.setDoctorName("Dr. Alice");
        d2.setType("Cardiology");
        d2.setLocation("NYC");
        d2.setDate(Date.valueOf("2026-02-01"));
        d2.setFee(220);

        d3 = new Doctor();
        d3.setDoctorName("Dr. Smith");
        d3.setType("Dermatology");
        d3.setLocation("NYC");
        d3.setDate(Date.valueOf("2026-02-01"));
        d3.setFee(150);
    }

    @Test
    void testLoadDoctorCache() {
        when(doctorRepo.findAll()).thenReturn(List.of(d1, d2, d3));

        doctorService.loadDoctorCache();

        assertEquals(2, DoctorService.doctorcache.size());
        assertTrue(DoctorService.doctorcache.containsKey("Cardiology"));
        assertTrue(DoctorService.doctorcache.containsKey("Dermatology"));

        // Correct expected sizes
        assertEquals(1, DoctorService.doctorcache.get("Dermatology").size());
        assertEquals(2, DoctorService.doctorcache.get("Cardiology").size());
    }

    @Test
    void testGetAllTypes() {


        Mockito.when(doctorRepo.findAll()).thenReturn(List.of(d1, d3));
        doctorService.loadDoctorCache();
        Set<String> types = doctorService.getAllTypes();

        assertEquals(Set.of("Dermatology", "Cardiology"), types);
    }

    @Test
    void testGetDoctorsByType() {
        when(doctorRepo.findAll()).thenReturn(List.of(d1, d2, d3));

        doctorService.loadDoctorCache();

        List<Doctor> cardiologyDoctors = doctorService.getDoctorsByType("Cardiology");

        assertEquals(2, cardiologyDoctors.size());

        // Do not assume ordering
        assertTrue(
                cardiologyDoctors.stream()
                        .anyMatch(d -> d.getDoctorName().equals("Dr. John"))
        );
    }

    @Test
    void testGetDoctorsByTypeNotFound() {
        when(doctorRepo.findAll()).thenReturn(List.of(d1, d2, d3));

        doctorService.loadDoctorCache();

        List<Doctor> result = doctorService.getDoctorsByType("Unknown");

        // Service returns null for unknown types
        assertNull(result);
    }
}
