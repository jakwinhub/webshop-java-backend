package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.exceptions.IdNotFoundException
import com.demoprojekt.webshop.model.CustomerResponse
import org.springframework.stereotype.Service


@Service
class CustomerRepositroy {

    val customers = listOf(
            CustomerResponse(
                    "1",
                   "Total",
                    "Surprise",
                    "total.surprise@gmail.com"
            )
    )

    fun findById(id: String): CustomerResponse {
        return customers.find { it.id == id } ?: throw IdNotFoundException("Customer with id $id not found")
    }
}