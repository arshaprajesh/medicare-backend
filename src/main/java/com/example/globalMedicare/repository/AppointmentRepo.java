package com.example.globalMedicare.repository;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AppointmentRepo  extends  JpaRepository <Patient, Integer> {
}
