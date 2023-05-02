package com.demoprojekt.webshop.client

import com.demoprojekt.webshop.model.JokeResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.util.*

interface ChuckNorrisFactsClient {
    fun getRandomFact(): JokeResponse
}

// prod -> production, cloud
// sandbox -> sandbox, cloud
// acceptance -> acceptance, cloud
// local ->

@Profile(value = ["cloud"])
@Service
class ChuckNorrisFactsRestClient(
        @Value("\${chuckNorrisApi.baseUrl}")
        val baseUrl: String) : ChuckNorrisFactsClient {

    val restTemplate = RestTemplate()

    override fun getRandomFact(): JokeResponse {
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

@Profile("!cloud")
@Service
class ChuckNorrisFactsMockClient() : ChuckNorrisFactsClient {

    override fun getRandomFact(): JokeResponse {
        return JokeResponse(
                categories = emptyList(),
                createdAt = LocalDateTime.now(),
                iconUrl = "",
                id = UUID.randomUUID().toString(),
                url = "",
                value = "This is a Chuck Norris fact"
        )
    }
}