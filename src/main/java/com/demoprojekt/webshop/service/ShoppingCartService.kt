package com.demoprojekt.webshop.service

import com.demoprojekt.webshop.entity.OrderEntity
import com.demoprojekt.webshop.entity.ProductEntity
import com.demoprojekt.webshop.exceptions.IdNotFoundException
import com.demoprojekt.webshop.model.OrderPositionResponse
import com.demoprojekt.webshop.model.ShoppingCartResponse
import com.demoprojekt.webshop.repository.OrderPositionEntity
import com.demoprojekt.webshop.repository.OrderRepository
import com.demoprojekt.webshop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
        val orderRepository: OrderRepository,
        val productRepository: ProductRepository
) {

    fun getShoppingCartForCustomer(customerId: String): ShoppingCartResponse {

        val orders: List<OrderEntity> = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId)
        val orderPositions = orders
                .flatMap { it.orderPositions }
                .map { OrderService.mapToResponse(it) }

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

    fun calculateSumForCart(orderPositions: List<OrderPositionResponse>, deliveryCost: Long): Long {
        val positionAmounts: List<Long> = orderPositions.map {
            val product: ProductEntity = productRepository
                    .findById(it.productId)
                    .orElseThrow { throw IdNotFoundException("Product with id ${it.productId} not found") }
            if (it.quantity <= 0) {
                throw IllegalArgumentException("Order Position with quantity of ${it.quantity} is not allowed")
            }
            it.quantity * product.priceInCent
        }
        val positionSum = positionAmounts.sumOf { it.toInt() }
        return positionSum + deliveryCost
    }
}
