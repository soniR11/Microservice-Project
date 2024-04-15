package com.prj.PaymentService.service;

import com.prj.PaymentService.entity.PaymentDetail;
import com.prj.PaymentService.modal.PaymentRequest;
import com.prj.PaymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceimpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("PaymentRequest in PaymentServiceImpl" + paymentRequest);
        PaymentDetail paymentDetails = PaymentDetail.builder()
                .amount(paymentRequest.getAmount())
                .paymentDate(Instant.now())
                .paymentStatus("CREATED")
                .paymentMode(paymentRequest.getPaymentMode().name())
                .orderId(paymentRequest.getOrderId())
                .build();
        log.info("Payment Details in do Payment" + paymentDetails);
        paymentRepository.save(paymentDetails);
        return paymentDetails.getPaymentId();
    }
}
