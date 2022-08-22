package com.cognizant.training.controller;

import com.cognizant.training.model.Order;
import com.cognizant.training.model.Product;
import com.cognizant.training.repository.OrderRepository;
import com.cognizant.training.repository.OrderStatusRepository;
import com.cognizant.training.repository.ProductRepository;
import com.cognizant.training.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class CartContoller {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @PutMapping("users/{user_id}/cart")
    public ResponseEntity<String> updateCart(@RequestBody CartRequest cartRequest, @PathVariable Long user_id){
        Order order = getOrCreateOrder(user_id);

        Optional<Product> product = productRepo.findById(cartRequest.getProduct());

        if(product.isEmpty())
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);

        order.addProductToOrder(product.get(), cartRequest.getCount());
        orderRepo.saveAndFlush(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/{user_id}/cart")
    public ResponseEntity<Order> getAllCarts(@PathVariable Long user_id){
        Order order = getOrCreateOrder(user_id);
        return ResponseEntity.ok(order);
    }

    private Order getOrCreateOrder(Long userId) {
        Order order = orderRepo.findByOwnerId(userId);
        if (order == null) {
            order = new Order();
            order.setOwnerId(userId);
            order.setOrderStatus(orderStatusRepository.getByName("CREATING"));
            order = orderRepo.saveAndFlush(order);
        }

        return order;
    }

}
