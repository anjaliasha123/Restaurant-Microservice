package com.rmaplearner.userinfo.service;

import com.rmaplearner.userinfo.dto.UserDTO;
import com.rmaplearner.userinfo.entity.User;
import com.rmaplearner.userinfo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserDTO addUser(UserDTO userDTO) {
        User user = User.builder()
                .userName(userDTO.getUserName())
                .userPassword(userDTO.getUserPassword())
                .address(userDTO.getAddress())
                .city(userDTO.getCity())
                .build();
        User savedUser = userRepo.save(user);
        UserDTO savedUserDTO = UserDTO
                .builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .userPassword(savedUser.getUserPassword())
                .address(savedUser.getAddress())
                .city(savedUser.getCity())
                .build();
        return savedUserDTO;
    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {
        Optional<User> fetchedUser = userRepo.findById(userId);
        if(fetchedUser.isPresent()){
            User user = fetchedUser.get();
            UserDTO userDTO = UserDTO
                    .builder()
                    .id(user.getId())
                    .userName(user.getUserName())
                    .userPassword(user.getUserPassword())
                    .city(user.getCity())
                    .address(user.getAddress())
                    .userName(user.getUserName())
                    .build();
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
