package com.demoprojekt.webshop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        ApiInfoBuilder().title("Endpunkte für Frontend")
                                .contact(Contact("H&S Sooftware","" , "HundS@goat.com"))
                                .build()
                )
                .groupName("Frontend")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(Frontend::class.java))
                .paths(PathSelectors.any())
                .build()
    }

    @Bean
    fun customerRelations(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("CustomerRelations")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/customers/.*"))
                .build()
    }
}

annotation class Frontend