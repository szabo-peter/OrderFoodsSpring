package com.example.food.controller;

import com.example.food.model.Food;
import com.example.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFood(@RequestBody Food food){
        foodService.createFood(food);
    }

    @GetMapping("")
    public List<Food> getAllFood(){
        return foodService.getAllFood();
    }

    @GetMapping("/{id}")
    public Optional<Food> getAFoodByID(@PathVariable String id){
        return foodService.getAFoodByID(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFood(@PathVariable String id,@RequestBody Food food){
        foodService.updateFood(id, food);
    }
}
