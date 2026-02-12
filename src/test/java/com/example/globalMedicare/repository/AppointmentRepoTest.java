package com.example.globalMedicare.repository;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@EntityScan("com.example.globalMedicare.model")
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class AppointmentRepoTest {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Test
    void testSaveAppointmentWithRelations() {
        // Create patient
        Patient p = new Patient();
        p.setPatientUsername("john");
        p.setPatientPassword("123");
        patientRepo.save(p);

        // Create doctor
        Doctor d = new Doctor();
        d.setDoctorName("Dr. Smith");
        d.setType("Cardiology");
        d.setLocation("NYC");
        d.setDate(Date.valueOf("2026-02-01"));
        d.setFee(200);
        doctorRepo.save(d);

        // Create appointment
        AppointmentDetail appointment = new AppointmentDetail();
        appointment.setAppointment_location("NYC");
        appointment.setAppointment_date(Date.valueOf("2026-02-01"));
        appointment.setPatient(p);
        appointment.getDoctors().add(d);

        AppointmentDetail saved = appointmentRepo.save(appointment);

        // Assertions
        assertTrue(saved.getAppointmentId() > 0);
        assertEquals("NYC", saved.getAppointment_location());
        assertEquals(p, saved.getPatient());
        assertTrue(saved.getDoctors().contains(d));
    }
}
