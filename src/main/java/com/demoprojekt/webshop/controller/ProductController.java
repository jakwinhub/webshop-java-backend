package com.demoprojekt.webshop.controller;

import com.demoprojekt.webshop.exceptions.IdNotFoundException;
import com.demoprojekt.webshop.model.ProductCreateRequest;
import com.demoprojekt.webshop.model.ProductResponse;
import com.demoprojekt.webshop.model.ProductUpdateRequest;
import com.demoprojekt.webshop.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tag) {
        return productRepository.findAll(tag);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        Optional<ProductResponse> product = productRepository.findById(id);
        if (product.isPresent())
            return ResponseEntity.ok(product.get());
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {
        ProductResponse response = new ProductResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        return productRepository.save(response);
    }

    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(
            @RequestBody ProductUpdateRequest request,
            @PathVariable String id
    ) {
        final ProductResponse product = productRepository.findById(id)
                .orElseThrow(() ->
                        new IdNotFoundException(
                                "Product with id " + id + " not found",
                                HttpStatus.BAD_REQUEST)
                );
        final ProductResponse updatedProduct = new ProductResponse(
                product.getId(),
                request.getName() == null ? product.getName() : request.getName(),
                request.getDescription() == null ? product.getDescription() : request.getDescription(),
                request.getPriceInCent() == null ? product.getPriceInCent() : request.getPriceInCent(),
                product.getTags()
        );


        return productRepository.save(updatedProduct);
    }
}

