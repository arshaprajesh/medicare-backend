package com.example.globalMedicare.controller;

import com.example.globalMedicare.model.Payment;
import com.example.globalMedicare.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create/{appointmentId}")
    public Payment createPayment(@RequestBody Payment data, @PathVariable int appointmentId) {
        System.out.println("Received payment: " + data);
        return paymentService.createPayment(data, appointmentId);

    }


}
