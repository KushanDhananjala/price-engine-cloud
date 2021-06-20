package com.kushan.priceenginecloud.utility;

public class Util {

    public static Integer[] generateArray(Integer elements) {
        Integer[] response = new Integer[elements];

        for (int i = 1; i <= elements; i++) {
            response[i - 1] = i;
        }

        return response;
    }
}
