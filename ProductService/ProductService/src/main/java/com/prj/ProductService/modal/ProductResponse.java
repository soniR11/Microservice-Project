package com.prj.ProductService.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {


    private String productName;
    private double productPrice;
    private int quantity;
}
