package com.rmaplearner.foodCatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
