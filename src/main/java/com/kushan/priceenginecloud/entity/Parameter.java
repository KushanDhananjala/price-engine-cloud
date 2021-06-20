package com.kushan.priceenginecloud.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "parameter")
@Data
public class Parameter {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "labor_percentage")
    private Double laborPercentage;
    @Column(name = "discount_eligible_cartons")
    private Integer discountEligibleCartons;
    @Column(name = "carton_discount")
    private Double cartonDiscount;

}
