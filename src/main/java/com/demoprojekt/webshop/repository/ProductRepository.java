package com.demoprojekt.webshop.repository;

import com.demoprojekt.webshop.model.ProductResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
    List<ProductResponse> products = Arrays.asList(
            new ProductResponse(
                    "1",
                    "AMD Ryzen 9 5950 X",
                    "Lorem Ipsum",
                    79900,
                    Arrays.asList("AMD", "Processor")),
            new ProductResponse(
                    "2",
                    "Intel Core i9-9900KF",
                    "Lorem Ipsum",
                    37900,
                    Arrays.asList("Intel", "Processor")),
            new ProductResponse(
                    "3",
                    "NVIDIA GeForece GTX 1080 Ti Black Edition",
                    "Lorem Ipsum",
                    74900,
                    Arrays.asList("NVIDIA", "Graphics"))
    );

    public List<ProductResponse> findAll(String tag) {

        if (tag == null)
            return products;
        else {
            String lowercaseTag = tag.toLowerCase();

            return products.stream()
                    .filter(p -> lowercaseTags(p).contains(lowercaseTag))
                    .collect(Collectors.toList());
        }
    }

    private List<String> lowercaseTags(ProductResponse p) {
        List<String> tags = p.getTags();
        return tags.stream().map(String::toLowerCase).collect(Collectors.toList());
    }
}