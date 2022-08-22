package com.cognizant.training.repository;

import com.cognizant.training.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOwnerId(Long id);
}
