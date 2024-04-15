package com.prj.ProductService.service;

import com.prj.ProductService.entity.Product;
import com.prj.ProductService.exception.ProductServiceException;
import com.prj.ProductService.modal.ProductRequest;
import com.prj.ProductService.modal.ProductResponse;
import com.prj.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public ProductResponse getProductById(Long productId) throws ProductServiceException {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException("Product id not found", "Product_not_Found"));
        ProductResponse productResponse = ProductResponse.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .quantity(product.getQuantity())
                .build();

        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId, int quantity) throws ProductServiceException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException("Product id not found", "Product_not_Found"));
        if (product instanceof Product) {
            if (product.getQuantity() < quantity) {
                throw new ProductServiceException("not having enough quantity of product", "NOT_ENOUGH_QUANTITY");
            }
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }

    }
}
