package com.example.globalMedicare.controller;

import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashSet;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    void testGetDoctorTypes() throws Exception {
        List<String> mockTypes = List.of("Cardiologist", "Dermatologist", "Dentist");
        Mockito.when(doctorService.getAllTypes()).thenReturn(new HashSet<>(mockTypes));
        mockMvc.perform(get("/doctors/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$", hasItems("Cardiologist", "Dermatologist", "Dentist")));
    }

    @Test
    void testGetDoctorsByType() throws Exception {
        Doctor d1 = new Doctor();
        d1.setDoctorName("Dr. John");
        d1.setType("Cardiologist");
        d1.setLocation("NYC");
        d1.setDate(Date.valueOf("2024-01-01"));
        d1.setFee(500);

        Doctor d2 = new Doctor();
        d2.setDoctorName("Dr. Smith");
        d2.setType("Cardiologist");
        d2.setLocation("LA");
        d2.setDate(Date.valueOf("2024-01-02"));
        d2.setFee(600);

        List<Doctor> mockDoctors = List.of(d1, d2);

        Mockito.when(doctorService.getDoctorsByType(eq("Cardiologist")))
                .thenReturn(mockDoctors);

        mockMvc.perform(get("/doctors/type/Cardiologist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].doctor_name").value("Dr. John"))
                .andExpect(jsonPath("$[1].doctor_name").value("Dr. Smith"));
    }

    @Test
    void testGetDoctorsByTypeEmpty() throws Exception {
        Mockito.when(doctorService.getDoctorsByType(eq("UnknownType")))
                .thenReturn(List.of());

        mockMvc.perform(get("/doctors/type/UnknownType"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}

