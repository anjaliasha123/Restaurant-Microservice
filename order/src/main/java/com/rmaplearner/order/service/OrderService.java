package com.rmaplearner.order.service;

import com.rmaplearner.order.dto.OrderDTO;
import com.rmaplearner.order.dto.OrderDTOFE;
import com.rmaplearner.order.dto.UserDTO;
import com.rmaplearner.order.entity.Order;
import com.rmaplearner.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    SequenceGenerator sequenceGenerator;
    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveInDB(OrderDTOFE orderDTOFE) {
        Integer orderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDTOFE.getUserId());
        Order order = Order
                .builder()
                .orderId(orderId)
                .foodItemsList(orderDTOFE.getFoodItemsList())
                .restaurantDTO(orderDTOFE.getRestaurantDTO())
                .userDTO(userDTO)
                .build();
        Order savedOrder = orderRepo.save(order);
        OrderDTO savedOrderDto = OrderDTO
                .builder()
                .orderId(savedOrder.getOrderId())
                .foodItemsList(savedOrder.getFoodItemsList())
                .restaurantDTO(savedOrder.getRestaurantDTO())
                .userDTO(savedOrder.getUserDTO())
                .build();
        return savedOrderDto;
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USERINFO-SERVICE/user/fetchUserById/"+userId, UserDTO.class);
    }
}
