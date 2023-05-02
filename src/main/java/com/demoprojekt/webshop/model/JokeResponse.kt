package com.demoprojekt.webshop.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class JokeResponse (
        val categories: Array<String>,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.n")
        @JsonProperty("created_at")
        val createdAt: LocalDateTime,
        @JsonProperty("icon_url")
        val iconUrl: String,
        val id: String,
        @JsonProperty("updated_at")
        val updatedAt: String,
        val url: String,
        val value: String
)