package com.prj.ProductService.service;

import com.prj.ProductService.exception.ProductServiceException;
import com.prj.ProductService.modal.ProductRequest;
import com.prj.ProductService.modal.ProductResponse;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);


    ProductResponse getProductById(Long productId) throws ProductServiceException;

    void reduceQuantity(Long productId, int quantity) throws ProductServiceException;
}
