package com.meshal.taskenterdatabaseworld.controller;

import com.meshal.taskenterdatabaseworld.bo.CreateUserRequest;
import com.meshal.taskenterdatabaseworld.bo.UserResponse;
import com.meshal.taskenterdatabaseworld.entity.UserEntity;
import com.meshal.taskenterdatabaseworld.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppController {
    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllGuests() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            UserEntity userEntity = userService.createUser(request);

            if (userEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            UserResponse response = new UserResponse(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<UserResponse> updateUserStatus(@RequestParam Long userId, @RequestParam String status) {
        try {
            UserEntity userEntity = userService.updateUserStatus(userId, status);

            if (userEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            UserResponse response = new UserResponse(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/searchUsers")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String status) {
        try {
            List<UserResponse> filteredUsers = userService.searchUsersByStatus(status);

            return ResponseEntity.status(HttpStatus.OK).body(filteredUsers);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
