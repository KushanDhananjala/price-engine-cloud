package com.kushan.priceenginecloud.service;

import com.kushan.priceenginecloud.dto.ProductDto;
import com.kushan.priceenginecloud.dto.ProductPriceDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    List<ProductPriceDto> getPriceForProductAndQuantity(Long productId, Integer[] quantity);

}
