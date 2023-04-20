package com.demoprojekt.webshop.model;

public class ProductUpdateRequest {

    private final String name;
    private final String description;
    private final Integer priceInCent;

    public ProductUpdateRequest(String name, String description, Integer priceInCent) {
        this.name = name;
        this.description = description;
        this.priceInCent = priceInCent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriceInCent() {
        return priceInCent;
    }
}
