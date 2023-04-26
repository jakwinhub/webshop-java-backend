package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.entity.OrderEntity
import com.demoprojekt.webshop.model.OrderStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import java.util.*

@Suppress("DEPRECATION")
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    lateinit var repository: OrderRepository

    @Autowired
    lateinit var customerRepository: CustomerRepositroy

    @BeforeEach
    fun setupTestData() {
        customerRepository.save(CustomerEntity(
                "234", "", "", "", ""
        ))
        customerRepository.save(CustomerEntity(
                "other-customer-id", "", "", "", ""
        ))
    }

    @Test
    fun `find all on empty db - returns empty list`() {

        // when
        val result = repository.findAll()

        // then
        assertThat(result).isEmpty()

    }

    @Test
    fun `find all after saving order - returns saved order`() {
        // given
        val orderEntity = createOrderEntity("234", OrderStatus.NEW)
        repository.save(orderEntity)

        // when
        val result = repository.findAll().first()

        // then
        assertThat(result).isEqualToIgnoringGivenFields(orderEntity, "orderPositions")
        assertThat(result.orderPositions).isEmpty()

    }

    @Test
    fun `findAllByCustomerIdWhereOrderStatusIsNew - returns expected order`() {
        // given
        val order = createOrderEntity("234", OrderStatus.NEW)
        repository.save(order)
        repository.save(createOrderEntity("234", OrderStatus.CANCELED))
        repository.save(createOrderEntity("other-customer-id", OrderStatus.NEW))

        // when
        val result = repository.findAllByCustomerIdWhereOrderStatusIsNew("234")

        // then
        assertThat(result.size).isEqualTo(1)
        val firstResult = result.first()
        assertThat(firstResult).isEqualToIgnoringGivenFields(order, "orderPositions")
    }

    private fun createOrderEntity(customerId: String, status: OrderStatus) = OrderEntity(
            id = UUID.randomUUID().toString(),
            customerId = customerId,
            orderTime = LocalDateTime.now(),
            status = status,
            orderPositions = emptyList()
    )
}