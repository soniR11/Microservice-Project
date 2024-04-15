package com.prj.OrderService.service;

import com.prj.OrderService.client.PaymentService;
import com.prj.OrderService.client.ProductService;
import com.prj.OrderService.client.request.PaymentRequest;
import com.prj.OrderService.entity.Order;
import com.prj.OrderService.modal.OrderRequest;
import com.prj.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    PaymentService paymentService;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        log.info("Before Placing Order");
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("After Checking  product availability for product " + orderRequest.getProductId());
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .price(orderRequest.getAmount())
                .quantity(orderRequest.getQuantity())
                .OrderStatus("CREATED")
                .orderDate(Instant.now())
                .build();
        log.info("Order Placed");
        order = orderRepository.save(order);
        log.info("Calling Payment Service With Order Id :: " + order.getId());
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .amount(orderRequest.getAmount())
                .paymentMode(orderRequest.getPaymentMode())
                .orderId(order.getId())
                .build();
        log.info("Payment Request " + paymentRequest);
        String status = StringUtils.EMPTY;
        try {
            paymentService.doPayment(paymentRequest);
            status = "SUCCESS";
        } catch (Exception e) {
            log.info("Payment Request " + paymentRequest);
            status = "FAILED";
        }
        order.setOrderStatus(status);
        order = orderRepository.save(order);
        log.info("Order Placed Successfully after place order...... " + order.getId());
        return order.getId();
    }
}
