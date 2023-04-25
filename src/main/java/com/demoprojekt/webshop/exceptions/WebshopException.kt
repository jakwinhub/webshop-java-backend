package com.demoprojekt.webshop.exceptions

import org.springframework.http.HttpStatus

data class WebshopException(
        override val message: String,
        val statusCode: HttpStatus
) : RuntimeException(message)

class IdNotFoundException(
        override val message: String,
        val statusCode: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(message)