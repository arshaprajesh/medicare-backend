package com.example.globalMedicare.controller;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    void testMakeAppointmentSuccess() throws Exception {

        Patient patient = new Patient();
        patient.setPatientUsername("john");

        Doctor doctor = new Doctor();
        doctor.setDoctorName("Dr. Smith");

        AppointmentDetail appointment = new AppointmentDetail();
        appointment.setAppointment_location("NYC");
        appointment.setAppointment_date(Date.valueOf("2026-02-01"));
        appointment.setPatient(patient);
        appointment.getDoctors().add(doctor);

        // Mock must match request parameters EXACTLY
        Mockito.when(appointmentService.makeAppointment(
                eq(1), eq(2), eq("NYC"), eq(Date.valueOf("2026-02-01"))
        )).thenReturn(appointment);

        mockMvc.perform(post("/appointment/appointmentDetails")
                        .param("doctorId", "1")
                        .param("patientId", "2")
                        .param("location", "NYC")
                        .param("date", "2026-02-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appointment_location").value("NYC"))
                .andExpect(jsonPath("$.appointment_date").value("2026-02-01"));


    }
}
