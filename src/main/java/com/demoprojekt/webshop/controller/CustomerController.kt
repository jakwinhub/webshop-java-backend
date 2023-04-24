package com.demoprojekt.webshop.controller

import com.demoprojekt.webshop.model.ShoppingCartResponse
import com.demoprojekt.webshop.repository.CustomerEntity
import com.demoprojekt.webshop.repository.CustomerRepositroy
import com.demoprojekt.webshop.service.ShoppingCartService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Suppress("DEPRECATION")
@RestController
class CustomerController(
        val customerRepository: CustomerRepositroy,
        val shoppingCartService: ShoppingCartService
) {

    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): CustomerEntity {
        val customer: CustomerEntity = customerRepository.getOne(id)
        return customer
    }

    @GetMapping("/customers/{id}/shoppingcart")
    fun getShoppingCartByCustomerId(@PathVariable id: String): ShoppingCartResponse {
        return shoppingCartService.getShoppingCartForCustomer(id)
    }
}