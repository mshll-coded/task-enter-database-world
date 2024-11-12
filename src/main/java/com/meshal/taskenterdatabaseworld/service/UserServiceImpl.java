package com.meshal.taskenterdatabaseworld.service;

import com.meshal.taskenterdatabaseworld.bo.CreateUserRequest;
import com.meshal.taskenterdatabaseworld.entity.UserEntity;
import com.meshal.taskenterdatabaseworld.enums.Status;
import com.meshal.taskenterdatabaseworld.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return null;
        }

        String userStatus = request.getStatus() != null ? request.getStatus().toUpperCase() : "ACTIVE";
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(Status.valueOf(userStatus));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUserStatus(Long userId, String status) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();

            userEntity.setStatus(Status.valueOf(status.toUpperCase()));
            return userRepository.save(userEntity);
        }
        return null;
    }

    @Override
    public List<UserEntity> searchUsersByStatus(String status) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            if (userEntity.getStatus() == Status.valueOf(status.toUpperCase())) {
                userEntities.add(userEntity);
            }
        }
        return userEntities;
    }

}
