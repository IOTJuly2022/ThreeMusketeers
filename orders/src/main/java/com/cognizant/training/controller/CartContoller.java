package com.cognizant.training.controller;

import com.cognizant.training.model.Order;
import com.cognizant.training.model.Product;
import com.cognizant.training.repository.OrderRepository;
import com.cognizant.training.repository.ProductRepository;
import com.cognizant.training.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class CartContoller {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @PutMapping("v1/users/{user_id}/cart/{cart_id}")
    public ResponseEntity<String> findCart(@RequestBody CartRequest cartRequest, @PathVariable Long user_id, @PathVariable Long cart_id){
        Optional<Order> order = orderRepo.findById(cart_id);

        if (order.isEmpty())
            return new ResponseEntity<>("Order Not Found",HttpStatus.NOT_FOUND);
        if(!order.get().getOwnerId().equals(user_id))
            return new ResponseEntity<>("User Doesn't Own Cart", HttpStatus.BAD_REQUEST);

        Optional<Product> product = productRepo.findById(cartRequest.getProduct());

        if(product.isEmpty())
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);

        order.get().addProductToOrder(product.get(), cartRequest.getCount());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
