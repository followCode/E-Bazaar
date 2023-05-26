package com.ecommerce.orderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.orderservice.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}