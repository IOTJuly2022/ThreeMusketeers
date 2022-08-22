package com.cognizant.training.controller;

import com.cognizant.training.model.*;
import com.cognizant.training.repository.OrderRepository;
import com.cognizant.training.repository.ProductRepository;
import com.cognizant.training.request.CartRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/v1")
public class CartContoller {

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
    public ResponseEntity<Order> getAllCarts(@PathVariable Long user_id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Fake order for testing
        /*
        Order fakeOrder = new Order("user", new OrderStatus("in-cart"));
        Set<OrderDetail> fakeDetails = new HashSet<>();
        Product fakeProduct = new Product("4020 RTX", 5,5);

        OrderDetail fakeDetail = new OrderDetail(2,fakeOrder, fakeProduct);
        OrderDetail fakeDetail2 = new OrderDetail(7,fakeOrder, fakeProduct);

        fakeDetails.add(fakeDetail);
        fakeDetails.add(fakeDetail2);
        fakeOrder.setOrderDetails(fakeDetails);
         */
        //return new ResponseEntity.ok(orderRepo.findAllByOwner_Id(user.getId()).orElse(List.of()));
        return ResponseEntity.ok(orderRepo.findAllByOwner_Id(user.getId()).orElse(new Order()));

    }


}
