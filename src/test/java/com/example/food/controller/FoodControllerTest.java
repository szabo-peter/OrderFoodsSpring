package com.example.food.controller;

import com.example.food.model.Food;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class FoodControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void getFoodTest() throws Exception {
        mockMvc.perform(get("/api/foods"))
                .andExpect(status().isOk());
    }

    @Test
    void createFoodTest() throws Exception {
        Food food = new Food();
        food.setName("Túrós tészta");
        food.setDescription("Pörccel");
        food.setPrice(1500.0);
        String jsonString = mapper.writeValueAsString(food);
        mockMvc.perform(post("/api/foods").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllBands() {
        given().when()
                .get("/api/foods")
                .then()
                .assertThat()
                .statusCode(200);
    }
}