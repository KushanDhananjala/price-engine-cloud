package com.kushan.priceenginecloud.dto;

import com.kushan.priceenginecloud.enums.ProductLabel;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Double price;
    private Integer units;
    private ProductLabel label;
}
