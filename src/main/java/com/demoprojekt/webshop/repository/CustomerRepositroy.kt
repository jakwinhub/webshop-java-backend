package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.exceptions.IdNotFoundException
import com.demoprojekt.webshop.model.CustomerResponse
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

/*@Service
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
*/

interface CustomerRepositroy : JpaRepository<CustomerEntity, String> {

}

@Entity
@Table(name = "customers")
data class CustomerEntity(
        @Id val id: String,
        val firstName: String,
        val lastName: String,
        val email: String
)
