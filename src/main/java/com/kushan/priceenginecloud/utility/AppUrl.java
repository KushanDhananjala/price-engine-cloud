package com.kushan.priceenginecloud.utility;

public class AppUrl {
    public final static String API_CONTEXT_PATH = "/api/v1";
    public final static String PRODUCTS_API = "/products";
    // Product Controller
    public final static String GET_PRODUCT_PRICE = "/price";
    public final static String GET_PRODUCT_PRICE_LIST = "/price/list";

    private AppUrl() {
        throw new IllegalStateException("AppUrl.class");
    }

}
