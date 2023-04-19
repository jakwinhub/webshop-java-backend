package com.demoprojekt.webshop.controller

import com.demoprojekt.webshop.model.CustomerResponse
import com.demoprojekt.webshop.model.ShoppingCartResponse
import com.demoprojekt.webshop.repository.CustomerRepositroy
import com.demoprojekt.webshop.service.ShoppingCartService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
        val customerRepository: CustomerRepositroy,
        val shoppingCartService: ShoppingCartService
) {

    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): CustomerResponse {
        return customerRepository.findById(id)
    }

    @GetMapping("/customers/{id}/shoppingcart")
    fun getShoppingCartByCustomerId(@PathVariable id: String): ShoppingCartResponse {
        return shoppingCartService.getShoppingCartForCustomer(id)
    }
}