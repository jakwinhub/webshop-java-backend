package com.demoprojekt.webshop.client

import com.demoprojekt.webshop.model.JokeResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ChuckNorrisFactsClient (
        @Value("\${chuckNorrisApi.baseUrl}")
        val baseUrl: String) {

    val restTemplate = RestTemplate()

    fun getRandomFact(): JokeResponse {
        val jokeUrl = "$baseUrl/jokes/random"

        try {
            val entity: ResponseEntity<JokeResponse> = restTemplate.getForEntity(jokeUrl, JokeResponse::class.java)

            return entity.body ?: throw Exception()

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}