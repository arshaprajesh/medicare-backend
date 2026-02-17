package com.example.globalMedicare.model;
import jakarta.persistence.*;


@Entity
@Table(name = "payments")
public class Payment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")

    private int paymentId;
    private String holderName;
    private String cardName;
    private String cardNumber;



    private String expiryDate;
    private String cvv;
    private Long doctorFee;
    private Long totalAmount;


    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointmentId")
    private AppointmentDetail appointment;

    public int getPaymentId() {
        return paymentId;
    }


    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Long getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(Long doctorFee) {
        this.doctorFee = doctorFee;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public AppointmentDetail getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentDetail appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", holderName='" + holderName + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", doctorFee=" + doctorFee +
                ", totalAmount=" + totalAmount +
                ", appointment=" + appointment +
                '}';
    }
}
