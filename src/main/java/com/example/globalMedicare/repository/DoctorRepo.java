package com.example.globalMedicare.repository;
import com.example.globalMedicare.model.Doctor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DoctorRepo  extends  JpaRepository <Doctor, Integer> {
    

}

