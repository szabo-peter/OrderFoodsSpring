package com.example.food.controller;


import com.example.food.model.Order;
import com.example.food.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order){
        orderService.createOrder(order);
    }

    @GetMapping("")
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public Optional<Order> getAnOrderByID(@PathVariable String id){
        return orderService.getAnOrderByID(id);
    }
}
