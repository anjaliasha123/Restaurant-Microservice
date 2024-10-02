package com.rmaplearner.restaurantListing.service;

import com.rmaplearner.restaurantListing.dto.RestaurantDTO;
import com.rmaplearner.restaurantListing.entity.Restaurant;
import com.rmaplearner.restaurantListing.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;
    public List<RestaurantDTO> fetchAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantDTO> restaurantDTOS =  restaurants.stream()
                .map(
                        restaurant -> RestaurantDTO
                                .builder()
                                .id(restaurant.getId())
                                .address(restaurant.getAddress())
                                .city(restaurant.getCity())
                                .name(restaurant.getName())
                                .restaurantDescription(restaurant.getRestaurantDescription())
                                .build()
                ).collect(Collectors.toList());
        return restaurantDTOS;
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = Restaurant
                .builder()
                .name(restaurantDTO.getName())
                .address(restaurantDTO.getAddress())
                .city(restaurantDTO.getCity())
                .restaurantDescription(restaurantDTO.getRestaurantDescription())
                .build();
        Restaurant saved = restaurantRepo.save(restaurant);
        RestaurantDTO savedrestaurantDTO = RestaurantDTO
                .builder()
                .id(saved.getId())
                .address(saved.getAddress())
                .city(saved.getCity())
                .name(saved.getName())
                .restaurantDescription(saved.getRestaurantDescription())
                .build();
        return savedrestaurantDTO;
    }


    public ResponseEntity<RestaurantDTO> fetchRestaurantById(int id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            Restaurant rest = restaurant.get();
            RestaurantDTO restaurantDTO = RestaurantDTO
                    .builder()
                    .restaurantDescription(rest.getRestaurantDescription())
                    .name(rest.getName())
                    .city(rest.getCity())
                    .address(rest.getAddress())
                    .id(rest.getId())
                    .build();
            return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
