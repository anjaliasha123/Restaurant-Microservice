package com.rmaplearner.foodCatalog.dto;

import com.rmaplearner.foodCatalog.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodCateloguePage {
    private List<FoodItem> foodItemsList;
    private Restaurant restaurant;
}
