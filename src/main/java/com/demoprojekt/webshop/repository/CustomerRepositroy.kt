package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.model.CustomerResponse
import java.util.UUID

class CustomerRepositroy {

    val customers = listOf(
            CustomerResponse(
                    "1",
                   "Total",
                    "Surprise",
                    "total.surprise@gmail.com"
            )
    )

    fun findById(id: String): CustomerResponse? {
        return customers.find { it.id == id }
    }
}