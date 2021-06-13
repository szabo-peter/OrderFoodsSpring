package com.example.food.service;

import com.example.food.model.Food;
import com.example.food.repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FoodServiceTest {

    @InjectMocks
    private FoodService foodService;

    @Mock
    private FoodRepository foodRepository;

    @Test
    public void getAllFoodTest(){

        List<Food> foods = List.of(
                Food.builder().name("Alma").description("Finom").price(150.0).build(),
                Food.builder().name("BAnán").description("Finom").price(150.0).build(),
                Food.builder().name("Körte").description("Finom").price(150.0).build()
                );

        when(foodRepository.findAll()).thenReturn(foods);

        assertEquals(3,foodService.getAllFood().size());

    }
    @Test
    public void getAFoodByIdTest(){

        when(foodRepository.findById("001")).thenReturn(Optional.of(Food.builder().foodId("001").name("Alma").description("Finom").price(150.0).build()));

        assertEquals("Alma",foodService.getAFoodByID("001").get().getName());
    }

}