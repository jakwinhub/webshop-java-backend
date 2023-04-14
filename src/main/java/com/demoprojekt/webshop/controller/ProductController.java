package com.demoprojekt.webshop.controller;

import com.demoprojekt.webshop.model.ProductResponse;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {

    public List<ProductResponse> getAllProducts() {
        return Collections.emptyList();
    }
}
