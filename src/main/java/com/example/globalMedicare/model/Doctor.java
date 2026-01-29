package com.example.globalMedicare.model;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "doctors")
@Access(AccessType.FIELD)//This tells Hibernate:“Use the field annotations, not the getter names.”Now Hibernate will respect@Column(name = "doctorId") and will stop generating doctor_id.
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId") // matches DB column
    private int doctorId ;

    private String doctorName;
    private String type;
    private String location;
    private Date date ;
    private int fee ;

    


    @ManyToMany(mappedBy = "doctors")
    @JsonIgnore
    private Set<AppointmentDetail> appointments = new HashSet<>();


    public int getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Set<AppointmentDetail> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentDetail> appointments) {
        this.appointments = appointments;
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", fee=" + fee +
                '}';
    }

}

