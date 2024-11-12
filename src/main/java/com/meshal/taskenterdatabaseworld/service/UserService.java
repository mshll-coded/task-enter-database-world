package com.meshal.taskenterdatabaseworld.service;

import com.meshal.taskenterdatabaseworld.bo.CreateUserRequest;
import com.meshal.taskenterdatabaseworld.bo.UpdateStatusRequest;
import com.meshal.taskenterdatabaseworld.bo.UserResponse;
import com.meshal.taskenterdatabaseworld.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity createUser(CreateUserRequest request);

    UserEntity updateUserStatus(UpdateStatusRequest request);
}