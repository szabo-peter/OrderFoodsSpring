package com.example.food.service;

import com.example.food.exception.ValidateException;
import com.example.food.model.Food;
import com.example.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    public final FoodRepository foodRepository;

    public void createFood(Food food) {
        validate(food);
        foodRepository.save(food);
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public Optional<Food> getAFoodByID(String id) {
        if(foodRepository.findById(id).equals(Optional.empty())){
            throw new ValidateException("Valós ID-t adj meg!");
        }
        return foodRepository.findById(id);
    }

    public void updateFood(String id, Food food) {
        Food oldFood = foodRepository.findById(id).orElse(null);

        if(oldFood == null){
            throw new ValidateException("Nincs ilyen étel!");
        }
        foodRepository.save(food.toBuilder().foodId(oldFood.getFoodId())
                .build());
    }

    private void validate(Food food){
        if(!StringUtils.hasText(food.getName())){
            throw new ValidateException("Adj meg nevet!");
        }
        if(food.getPrice() == null || food.getPrice()<0){
            throw new ValidateException("Adj meg árat!");
        }
    }
}
