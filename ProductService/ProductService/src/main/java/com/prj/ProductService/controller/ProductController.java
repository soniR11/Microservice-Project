package com.prj.ProductService.controller;

import com.prj.ProductService.entity.Product;
import com.prj.ProductService.exception.ProductServiceException;
import com.prj.ProductService.modal.ProductRequest;
import com.prj.ProductService.modal.ProductResponse;
import com.prj.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        Long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long productId) throws ProductServiceException {
        ProductResponse productResponse =productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }


    @PutMapping("/reduceQuantity/{id}")
    public  ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId,@RequestParam int quantity) throws ProductServiceException {
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
