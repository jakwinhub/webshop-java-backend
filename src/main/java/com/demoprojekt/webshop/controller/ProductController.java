package com.demoprojekt.webshop.controller;

import com.demoprojekt.webshop.entity.ProductEntity;
import com.demoprojekt.webshop.exceptions.IdNotFoundException;
import com.demoprojekt.webshop.model.ProductCreateRequest;
import com.demoprojekt.webshop.model.ProductResponse;
import com.demoprojekt.webshop.model.ProductUpdateRequest;
import com.demoprojekt.webshop.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tag) {
        return productRepository
                .findAll() //alle produkt entitys aus datenbank
                .stream()  //wandeln die liste in einen stream um um filter zu benutzen
                .filter((productEntity) -> tag == null || productEntity.getTags().contains(tag)) //behalten nur die entitys die den gefilterten tag beinhalten
                .map(ProductController::mapToResponse)  //wandelt entity in response um
                .collect(Collectors.toList());   // list von responses wird generiert
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        ProductEntity product = productRepository.getOne(id);
        return mapToResponse(product);
    }

    @NotNull
    private static ProductResponse mapToResponse(ProductEntity product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPriceInCent(),
                product.getTags()
        );
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {
        ProductEntity productEntity = new ProductEntity(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        ProductEntity savedProduct = productRepository.save(productEntity);
        return mapToResponse(savedProduct);
    }

    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(
            @RequestBody ProductUpdateRequest request,
            @PathVariable String id
    ) {
        final ProductEntity product = productRepository.findById(id)
                .orElseThrow(() ->
                        new IdNotFoundException(
                                "Product with id " + id + " not found",
                                HttpStatus.BAD_REQUEST)
                );
        final ProductEntity updatedProduct = new ProductEntity(
                product.getId(),
                request.getName() == null ? product.getName() : request.getName(),
                request.getDescription() == null ? product.getDescription() : request.getDescription(),
                request.getPriceInCent() == null ? product.getPriceInCent() : request.getPriceInCent(),
                product.getTags()
        );


        ProductEntity savedProduct = productRepository.save(updatedProduct);
        return mapToResponse(savedProduct);
    }
}

