package com.example.globalMedicare.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "appointment_details")
public class AppointmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId") // matches DB column

    private int appointmentId ;

    private Date appointment_date;
    private String appointment_location;

    @ManyToOne  //mapping with patients
    @JoinColumn(name = "patientId", referencedColumnName = "patient_id") // match foreign key
    private Patient patient;

    @ManyToMany //mapping with doctors using join table doctor_appointments
    @JoinTable(
            name = "doctor_appointments",
            joinColumns = @JoinColumn(name = "appointmentId"),
            inverseJoinColumns = @JoinColumn(name = "doctorId")
    )
    private Set<Doctor> doctors = new HashSet<>();

    public int getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_location() {
        return appointment_location;
    }

    public void setAppointment_location(String appointment_location) {
        this.appointment_location = appointment_location;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    

}
