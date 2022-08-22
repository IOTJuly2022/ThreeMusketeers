package com.cognizant.training.controller;

import com.cognizant.training.model.*;
import com.cognizant.training.repository.OrderRepository;
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
public class CartController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @PutMapping("users/{user_id}/cart")
    public ResponseEntity<String> updateCart(@RequestBody CartRequest cartRequest, @PathVariable Long user_id){
        Optional<Order> order = orderRepo.findAllByOwner_Id(user_id);

        if (order.isEmpty())
            return new ResponseEntity<>("Order Not Found",HttpStatus.NOT_FOUND);

        Optional<Product> product = productRepo.findById(cartRequest.getProduct());

        if(product.isEmpty())
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);

        order.get().addProductToOrder(product.get(), cartRequest.getCount());
        orderRepo.saveAndFlush(order.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/{user_id}/cart")
    public ResponseEntity<Optional<Order>> getAllCarts(@PathVariable Long user_id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Order> order = orderRepo.findAllByOwner_Id((user.getId()));
        if(order.isPresent())
            return ResponseEntity.ok(order);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
