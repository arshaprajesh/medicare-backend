package com.example.globalMedicare.repository;
import com.example.globalMedicare.model.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface PatientRepo  extends  JpaRepository <Patient, Integer> {
    List<Patient> findByPatientUsername(String patientUsername);


}
