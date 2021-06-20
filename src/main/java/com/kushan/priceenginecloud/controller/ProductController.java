package com.kushan.priceenginecloud.controller;

import com.kushan.priceenginecloud.dto.ProductDto;
import com.kushan.priceenginecloud.dto.ProductPriceDto;
import com.kushan.priceenginecloud.service.ProductService;
import com.kushan.priceenginecloud.utility.AppUrl;
import com.kushan.priceenginecloud.utility.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(AppUrl.API_CONTEXT_PATH + AppUrl.PRODUCTS_API)
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(AppUrl.GET_PRODUCT_PRICE)
    public ResponseEntity<ProductPriceDto> getProductPrice(@RequestParam(name = "productId") Long productId, @RequestParam(name = "quantity", required = false, defaultValue = "1") Integer quantity) {
        return new ResponseEntity<>(productService.getPriceForProductAndQuantity(productId, new Integer[]{quantity}).get(0), HttpStatus.OK);
    }

    @GetMapping(AppUrl.GET_PRODUCT_PRICE_LIST)
    public ResponseEntity<List<ProductPriceDto>> getProductPriceList(@RequestParam(name = "productId") Long productId, @RequestParam(name = "limit", required = false, defaultValue = "50") Integer limit) {
        return new ResponseEntity<>(productService.getPriceForProductAndQuantity(productId, Util.generateArray(limit)), HttpStatus.OK);
    }

}
