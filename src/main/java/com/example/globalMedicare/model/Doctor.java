package com.example.globalMedicare.model;


import jakarta.persistence.*;

import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name= "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id") // matches DB column
    private int doctorId ;

    private String doctor_username;
    private String doctor_password;
    private String type;
    private String location;



    @ManyToMany(mappedBy = "doctors")
    private Set<AppointmentDetail> appointments = new HashSet<>();


    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctor_username() {
        return doctor_username;
    }

    public void setDoctor_username(String doctor_username) {
        this.doctor_username = doctor_username;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Set<AppointmentDetail> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentDetail> appointments) {
        this.appointments = appointments;
    }

}

