package com.demoprojekt.webshop.model;

import java.util.List;

public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private Integer priceInCent;
    private List<String> tags;

    public ProductResponse(String id, String name, String description, Integer priceInCent, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = descriptiob;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }

    public String getId() {
        return id;
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

    public List<String> getTags() {
        return tags;
    }
}
