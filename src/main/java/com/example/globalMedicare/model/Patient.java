package com.example.globalMedicare.model;


import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name= "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id") // matches DB column
    private int patientId ;

    private String patientUsername;
    private String patientPassword;
    private String created_by;
    private Date created_at;
    private String issued_by;
    private Date issued_at;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<AppointmentDetail> appointments = new HashSet<>();

    public int getPatientId() {
        return patientId;
    }
    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }
    public String getPatientPassword(){
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public String getCreated_by(){
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public Date getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(Date issued_at) {
        this.issued_at = issued_at;
    }

    public Set<AppointmentDetail> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentDetail> appointments) {
        this.appointments = appointments;
    }

   @Override
    public String toString() {
        return "Patient{" +
                "patient_username='" + patientUsername + '\'' +
                ", patient_password='" + patientPassword + '\'' +
                ", created_by='" + created_by + '\'' +
                ", created_at=" + created_at +
                ", issued_by='" + issued_by + '\'' +
                ", issued_at=" + issued_at +
                '}';
    }

}
