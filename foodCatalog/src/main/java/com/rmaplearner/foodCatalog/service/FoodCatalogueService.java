package com.rmaplearner.foodCatalog.service;

import com.rmaplearner.foodCatalog.dto.FoodCateloguePage;
import com.rmaplearner.foodCatalog.dto.FoodItemDto;
import com.rmaplearner.foodCatalog.dto.Restaurant;
import com.rmaplearner.foodCatalog.entity.FoodItem;
import com.rmaplearner.foodCatalog.repository.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    FoodItemRepo foodItemRepo;
    @Autowired
    RestTemplate restTemplate;

    public FoodItemDto addFoodItem(FoodItemDto foodItemDto){
        FoodItem foodItem = FoodItem
                .builder()
                .itemName(foodItemDto.getItemName())
                .itemDescription(foodItemDto.getItemDescription())
                .isVeg(foodItemDto.isVeg())
                .price(foodItemDto.getPrice())
                .restaurantId(foodItemDto.getRestaurantId())
                .quantity(foodItemDto.getQuantity())
                .build();
        FoodItem savedItem = foodItemRepo.save(foodItem);
        FoodItemDto savedDto = FoodItemDto
                .builder()
                .id(savedItem.getId())
                .itemName(savedItem.getItemName())
                .restaurantId(savedItem.getRestaurantId())
                .isVeg(savedItem.isVeg())
                .price(savedItem.getPrice())
                .quantity(savedItem.getQuantity())
                .itemDescription(savedItem.getItemDescription())
                .build();
        return  savedDto;
    }

    public FoodCateloguePage fetchFoodCataloguePageDetails(Integer restaurantId){
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCateloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCateloguePage foodCateloguePage = FoodCateloguePage.builder()
                .foodItemsList(foodItemList)
                .restaurant(restaurant)
                .build();
        return foodCateloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
