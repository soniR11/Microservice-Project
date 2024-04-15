package com.prj.PaymentService.Controller;

import com.prj.PaymentService.modal.PaymentRequest;
import com.prj.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest pymentRequest) {
        long paymentId = paymentService.doPayment(pymentRequest);
        return new ResponseEntity<Long>(paymentId, HttpStatus.OK);
    }


}
