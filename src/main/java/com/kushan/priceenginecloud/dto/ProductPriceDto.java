package com.kushan.priceenginecloud.dto;

import lombok.Data;

@Data
public class ProductPriceDto {
    private String productName;
    private Double price = 0.0;
    private Integer units;
    private boolean isCarton;
    private Integer cartonCount;
}
