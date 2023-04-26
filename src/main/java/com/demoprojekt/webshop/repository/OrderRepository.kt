package com.demoprojekt.webshop.repository

import com.demoprojekt.webshop.entity.OrderEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository : JpaRepository<OrderEntity, String> {
    @Query("SELECT e FROM OrderEntity e where e.status = 'NEW' and e.customerId = :customerId")
    abstract fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderEntity>
}

