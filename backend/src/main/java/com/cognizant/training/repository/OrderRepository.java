package com.cognizant.training.repository;

import com.cognizant.training.model.Order;
import com.cognizant.training.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findAllByOwner(User user);
}
