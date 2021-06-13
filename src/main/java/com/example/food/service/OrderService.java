package com.example.food.service;

import com.example.food.exception.ValidateException;
import com.example.food.model.Food;
import com.example.food.model.Order;
import com.example.food.model.User;
import com.example.food.repository.FoodRepository;
import com.example.food.repository.OrderRepository;
import com.example.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public void createOrder(Order order) {
        if(!StringUtils.hasText(order.getOrderedBy().getId())){
            if(!StringUtils.hasText(order.getOrderedBy().getEmail())){
                throw new ValidateException("Email megadása kötelező!");
            }
            if(!StringUtils.hasText(order.getOrderedBy().getFullAddress())){
                throw new ValidateException("Cím megadása kötelező!");
            }
            User validUser = User.builder()
                    .email(order.getOrderedBy().getEmail())
                    .fullAddress(order.getOrderedBy().getFullAddress())
                    .build();
            userRepository.save(validUser);
            order.setOrderedBy(validUser);
        }else{
            User user = userRepository.findById(order.getOrderedBy().getId()).orElse(null);
            if (user == null) {
                throw new ValidateException("Nincs ilyen ID-val rendelkező felshasználó!");
            }
            else{
                if(!user.getEmail().equals(order.getOrderedBy().getEmail())){
                    throw new ValidateException("Nem egyezik meg a megadott email a tárolt emaillel!");
                }
                if(!user.getFullAddress().equals(order.getOrderedBy().getFullAddress())){
                    throw new ValidateException("Nem egyezik meg a megadott cím a tárolt címmel!");
                }
            }
            order.setOrderedBy(user);
        }
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < order.getFoods().size(); i++) {
            Food food = foodRepository.findById(order.getFoods().get(i).getFoodId()).orElse(null);
            if (food == null) {
                throw new ValidateException("Nincs ilyen food!");
            } else {
                foods.add(food);
            }
        }
        order.setFoods(foods);
        orderRepository.save(order);
    }


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Optional<Order> getAnOrderByID(String id) {
        if(orderRepository.findById(id).equals(Optional.empty())){
            throw new ValidateException("Valós ID-t adj meg!");
        }
        return orderRepository.findById(id);
    }

}


