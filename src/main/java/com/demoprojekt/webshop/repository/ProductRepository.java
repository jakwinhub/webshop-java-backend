package com.demoprojekt.webshop.repository;

import com.demoprojekt.webshop.model.ProductCreateRequest;
import com.demoprojekt.webshop.model.ProductResponse;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {
    List<ProductResponse> products = new ArrayList<>();

    public ProductRepository() {
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "AMD Ryzen 9 5950 X",
                        "Lorem Ipsum",
                        79900,
                        Arrays.asList("AMD", "Processor")));

        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "Intel Core i9-9900KF",
                        "Lorem Ipsum",
                        37900,
                        Arrays.asList("Intel", "Processor")));

        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "NVIDIA GeForece GTX 1080 Ti Black Edition",
                        "Lorem Ipsum",
                        74900,
                        Arrays.asList("NVIDIA", "Graphics")));
    }

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

    public Optional<ProductResponse> findById(String id) {
        Optional<ProductResponse> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return product;
    }

    public void deleteById(String id) {
        this.products = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductCreateRequest request) {
        ProductResponse response = new ProductResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        products.add(response);
        return response;
    }
}