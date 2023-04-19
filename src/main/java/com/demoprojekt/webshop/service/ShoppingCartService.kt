package com.demoprojekt.webshop.service

import com.demoprojekt.webshop.exceptions.IdNotFoundException
import com.demoprojekt.webshop.model.OrderPositionResponse
import com.demoprojekt.webshop.model.OrderResponse
import com.demoprojekt.webshop.model.ProductResponse
import com.demoprojekt.webshop.model.ShoppingCartResponse
import com.demoprojekt.webshop.repository.OrderPositionRepository
import com.demoprojekt.webshop.repository.OrderRepository
import com.demoprojekt.webshop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
        val orderRepository: OrderRepository,
        val orderPositionRepository: OrderPositionRepository,
        val productRepository: ProductRepository
) {

    fun getShoppingCartForCustomer(customerId: String): ShoppingCartResponse {

        val orders: List<OrderResponse> = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId)
        val orderIds = orders.map { it.id }

        val orderPositions = orderPositionRepository.findAllByOrderIds(orderIds)
        val deliveryCost = 800L  // TODO: feature to select delivery method
        val totalAmount = calculateSumForCart(orderPositions, deliveryCost)

        return ShoppingCartResponse(
                customerId = customerId,
                orderPositions = orderPositions,
                deliveryOption = "STANDART",
                deliveryCostInCent = deliveryCost,
                totalAmountInCent = totalAmount
        )
    }

    private fun calculateSumForCart(orderPositions: List<OrderPositionResponse>, deliveryCost: Long): Long {
        val positionAmounts: List<Long> = orderPositions.map {
            val product: ProductResponse = productRepository
                    .findById(it.productId)
                    .orElseThrow { throw IdNotFoundException("Product with id ${it.productId} not found") }
            it.quantity * product.priceInCent
        }
        val positionSum = positionAmounts.sumOf { it.toInt() }
        return positionSum + deliveryCost
    }
}
