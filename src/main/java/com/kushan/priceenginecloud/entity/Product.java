package com.kushan.priceenginecloud.entity;

import com.kushan.priceenginecloud.enums.ProductLabel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "units", nullable = false)
    private Integer units;
    @Column(name = "label", nullable = true)
    private ProductLabel label;

}
