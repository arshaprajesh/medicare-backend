package com.example.globalMedicare.service;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.model.Patient;
import com.example.globalMedicare.repository.AppointmentRepo;
import com.example.globalMedicare.repository.DoctorRepo;
import com.example.globalMedicare.repository.PatientRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepo appointmentRepo;

    @Mock
    private PatientRepo patientRepo;

    @Mock
    private DoctorRepo doctorRepo;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void testMakeAppointmentSuccess() {
        int patientId = 1;
        int doctorId = 10;
        String location = "NYC";
        Date date = Date.valueOf("2026-02-01");

        // Mock patient (no ID setter needed)
        Patient patient = new Patient();
        patient.setPatientUsername("john");

        // Mock doctor (no ID setter needed)
        Doctor doctor = new Doctor();
        doctor.setDoctorName("Dr. Smith");
        doctor.setType("Cardiology");

        // Mock repo behavior
        when(patientRepo.getReferenceById(patientId)).thenReturn(patient);
        when(doctorRepo.getReferenceById(doctorId)).thenReturn(doctor);

        AppointmentDetail saved = new AppointmentDetail();
        saved.setAppointment_location(location);
        saved.setAppointment_date(date);
        saved.setPatient(patient);
        saved.getDoctors().add(doctor);

        when(appointmentRepo.save(any(AppointmentDetail.class))).thenReturn(saved);

        // Call service
        AppointmentDetail result =
                appointmentService.makeAppointment(doctorId, patientId, location, date);

        // Verify mapping
        assertEquals(location, result.getAppointment_location());
        assertEquals(date, result.getAppointment_date());
        assertEquals(patient, result.getPatient());
        assertTrue(result.getDoctors().contains(doctor));

        verify(appointmentRepo, times(1)).save(any(AppointmentDetail.class));
    }

    @Test
    void testMakeAppointmentPatientNotFound() {
        when(patientRepo.getReferenceById(1))
                .thenThrow(new RuntimeException("Patient not found"));

        assertThrows(RuntimeException.class, () ->
                appointmentService.makeAppointment(10, 1, "NYC", Date.valueOf("2026-02-01"))
        );
    }

    @Test
    void testMakeAppointmentDoctorNotFound() {
        Patient patient = new Patient();
        patient.setPatientUsername("john");

        when(patientRepo.getReferenceById(1)).thenReturn(patient);
        when(doctorRepo.getReferenceById(10))
                .thenThrow(new RuntimeException("Doctor not found"));

        assertThrows(RuntimeException.class, () ->
                appointmentService.makeAppointment(10, 1, "NYC", Date.valueOf("2026-02-01"))
        );
    }
}
