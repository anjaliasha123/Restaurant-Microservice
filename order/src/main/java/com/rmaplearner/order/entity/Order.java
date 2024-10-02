package com.rmaplearner.order.entity;

import com.rmaplearner.order.dto.FoodItemDTO;
import com.rmaplearner.order.dto.RestaurantDTO;
import com.rmaplearner.order.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "order")
public class Order {
    private Integer orderId;
    private List<FoodItemDTO> foodItemsList;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}
