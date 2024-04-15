package com.prj.PaymentService.service;

import com.prj.PaymentService.modal.PaymentRequest;

public interface PaymentService {
    long doPayment(PaymentRequest pymentRequest);

}
