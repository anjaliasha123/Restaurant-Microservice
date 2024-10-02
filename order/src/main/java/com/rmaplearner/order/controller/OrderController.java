package com.rmaplearner.order.controller;

import com.rmaplearner.order.dto.OrderDTO;
import com.rmaplearner.order.dto.OrderDTOFE;
import com.rmaplearner.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFE orderDTOFE){
        OrderDTO orderSaved = orderService.saveInDB(orderDTOFE);
        return new ResponseEntity<>(orderSaved, HttpStatus.CREATED);
    }
}
