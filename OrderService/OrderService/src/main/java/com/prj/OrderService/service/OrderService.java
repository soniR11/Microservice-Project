package com.prj.OrderService.service;

import com.prj.OrderService.entity.Order;
import com.prj.OrderService.modal.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderService  {
    Long placeOrder(OrderRequest orderRequest);
}
