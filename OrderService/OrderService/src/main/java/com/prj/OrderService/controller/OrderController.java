package com.prj.OrderService.controller;

import com.prj.OrderService.modal.OrderRequest;
import com.prj.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {

        Long OrderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<Long>(OrderId, HttpStatus.OK);
    }

}
