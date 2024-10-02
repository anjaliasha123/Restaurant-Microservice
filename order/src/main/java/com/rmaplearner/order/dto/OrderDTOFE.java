package com.rmaplearner.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTOFE {
    private List<FoodItemDTO> foodItemsList;
    private RestaurantDTO restaurantDTO;
    private Integer userId;
}
