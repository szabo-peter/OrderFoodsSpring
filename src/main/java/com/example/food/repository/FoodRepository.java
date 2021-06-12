package com.example.food.repository;

import com.example.food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, String> {
}
