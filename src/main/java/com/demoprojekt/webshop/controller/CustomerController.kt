package com.demoprojekt.webshop.controller

import com.demoprojekt.webshop.model.CustomerResponse
import com.demoprojekt.webshop.repository.CustomerRepositroy
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
        val customerRepository: CustomerRepositroy
) {


    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): ResponseEntity<CustomerResponse> {
        val response = customerRepository.findById(id)
        return if (response != null)
            ResponseEntity.ok(response)
        else
            return ResponseEntity.notFound().build()
    }
}