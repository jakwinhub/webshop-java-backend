package com.demoprojekt.webshop.service

import com.demoprojekt.webshop.model.OrderCreateRequest
import com.demoprojekt.webshop.model.OrderPositionCreateRequest
import com.demoprojekt.webshop.model.OrderPositionResponse
import com.demoprojekt.webshop.model.OrderResponse
import com.demoprojekt.webshop.repository.CustomerRepositroy
import com.demoprojekt.webshop.repository.OrderPositionRepository
import com.demoprojekt.webshop.repository.OrderRepository
import com.demoprojekt.webshop.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepositroy: OrderRepository,
        val orderPositionRepositroy: OrderPositionRepository,
        val customerRepository: CustomerRepositroy
) {


    fun createOrder(request: OrderCreateRequest): OrderResponse {

        customerRepository.findById(request.customerId)
                ?: throw Exception("Customer not found")

        return orderRepositroy.save(request)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderPositionCreateRequest): OrderPositionResponse {

        orderRepositroy.findById(orderId)
                ?: throw Exception("Order not found")

        if (productRepository.findById(request.productId).isEmpty)
            throw Exception("Product not found")

        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                productId = request.productId,
                quantity = request.quantity
        )
        orderPositionRepositroy.save(orderPositionResponse)

        return orderPositionResponse
    }
}