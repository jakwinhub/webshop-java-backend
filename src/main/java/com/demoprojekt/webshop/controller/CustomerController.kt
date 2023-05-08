package com.demoprojekt.webshop.controller

import com.demoprojekt.webshop.client.ChuckNorrisFactsClient
import com.demoprojekt.webshop.model.CustomerResponse
import com.demoprojekt.webshop.model.ShoppingCartResponse
import com.demoprojekt.webshop.repository.CustomerEntity
import com.demoprojekt.webshop.repository.CustomerRepositroy
import com.demoprojekt.webshop.service.ShoppingCartService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Suppress("DEPRECATION")
@RestController
class CustomerController(
        val customerRepository: CustomerRepositroy,
        val shoppingCartService: ShoppingCartService,
        val chuckNorrisFactsClient: ChuckNorrisFactsClient
) {
    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): CustomerResponse {

        val jokeResponse = chuckNorrisFactsClient.getRandomFact()

        val customer: CustomerEntity = customerRepository.getOne(id)
        return CustomerResponse(
                id = customer.id,
                firstName = customer.firstName,
                lastName = customer.lastName,
                email = customer.email,
                joke = jokeResponse.value
        )
    }

    @ApiOperation(value = "Load shopping cart by customer id")
    @GetMapping("/customers/{id}/shoppingcart")
    fun getShoppingCartByCustomerId(
            @ApiParam(value = "Customer Id", required = true)
            @PathVariable id: String): ShoppingCartResponse {
        return shoppingCartService.getShoppingCartForCustomer(id)
    }
}