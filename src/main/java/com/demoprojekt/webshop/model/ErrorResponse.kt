package com.demoprojekt.webshop.model

import org.springframework.http.HttpStatus

data class ErrorResponse (
        val code : HttpStatus,
        val message : String
) {
}