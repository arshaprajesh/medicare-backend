package com.example.globalMedicare.service;

import com.example.globalMedicare.model.AppointmentDetail;
import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.model.Payment;
import com.example.globalMedicare.repository.AppointmentRepo;
import com.example.globalMedicare.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    public Payment createPayment(Payment data,int appointmentId){
        // 1. Fetch appointment

        AppointmentDetail appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // 2. Create new payment object
        Payment payment = new Payment();
        // 3. Copy card details from request body
         payment.setCardName(data.getCardName());
         payment.setCardNumber(data.getCardNumber());
         payment.setExpiryDate(data.getExpiryDate());
         payment.setCvv(data.getCvv());

        // 2. Set appointment in data

        payment.setAppointment(appointment);

        //fetch patient name from appointment(appointment have patient details (   private Patient patient)

        String patientName = appointment.getPatient().getPatientUsername();
        payment.setHolderName(patientName);

        //fetch fee from appointment(in AppointmentDetails(private Set<Doctor> doctors = new HashSet<>();)s0 we cant fetch directly ,need to do looping)

       Long totalFee = 0L;
       for(Doctor d : appointment.getDoctors()) {
           if (d != null ) {
               totalFee = totalFee + d.getFee();
           }
       }
        payment.setDoctorFee(totalFee);

        //calculate total amount

        payment.setTotalAmount(totalFee);

        //save payment

        return paymentRepo.save(payment);



    }
}