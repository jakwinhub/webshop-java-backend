package com.demoprojekt.webshop.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@Suppress("DEPRECATION")
data class CustomerResponse(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
        val joke: String = ""
)