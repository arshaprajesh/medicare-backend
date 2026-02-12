package com.example.globalMedicare.repository;

import com.example.globalMedicare.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.ActiveProfiles;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.sql.init.mode=never"   // prevents loading data.sql
})
class PatientRepoTest {

    @Autowired
    private PatientRepo patientRepo;

    @Test
    void testSaveAndFindByUsername() {

        // Ensure clean DB for this test
        patientRepo.deleteAll();

        Patient p = new Patient();
        p.setPatientUsername("john123");
        p.setPatientPassword("secret");
        p.setCreated_by("system");
        p.setCreated_at(new Date());
        p.setIssued_by("system");
        p.setIssued_at(new Date());

        patientRepo.save(p);
        List<Patient> found = patientRepo.findByPatientUsername("john123");

        assertEquals(1, found.size());
        assertEquals("john123", found.get(0).getPatientUsername());
    }
}
