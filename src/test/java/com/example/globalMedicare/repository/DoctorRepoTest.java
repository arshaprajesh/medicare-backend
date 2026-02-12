package com.example.globalMedicare.repository;

import com.example.globalMedicare.model.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@EntityScan("com.example.globalMedicare.model")
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})

class DoctorRepoTest {

    @Autowired
    private DoctorRepo doctorRepo;

    @Test
    void testSaveAndFindAll() {
        Doctor d1 = new Doctor();
        d1.setDoctorName("Dr. John");
        d1.setType("Cardiology");
        d1.setLocation("NYC");
        d1.setDate(Date.valueOf("2026-02-01"));
        d1.setFee(200);

        Doctor d2 = new Doctor();
        d2.setDoctorName("Dr. Smith");
        d2.setType("Dermatology");
        d2.setLocation("NYC");
        d2.setDate(Date.valueOf("2026-02-01"));
        d2.setFee(150);

        doctorRepo.save(d1);
        doctorRepo.save(d2);

        List<Doctor> result = doctorRepo.findAll();

        assertEquals(2, result.size());
    }
}
