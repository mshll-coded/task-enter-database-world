package com.meshal.taskenterdatabaseworld.service;

import com.meshal.taskenterdatabaseworld.bo.CreateUserRequest;
import com.meshal.taskenterdatabaseworld.bo.UpdateStatusRequest;
import com.meshal.taskenterdatabaseworld.bo.UserResponse;
import com.meshal.taskenterdatabaseworld.entity.UserEntity;
import com.meshal.taskenterdatabaseworld.enums.Status;
import com.meshal.taskenterdatabaseworld.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Arrays;
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
        if (!validateStatus(userStatus)) {
            return null;
        }

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

            String userStatus = status.toUpperCase();
            if (!validateStatus(userStatus)) {
                return null;
            }

            userEntity.setStatus(Status.valueOf(userStatus));
            return userRepository.save(userEntity);
        }
        return null;
    }

    public static boolean validateStatus(String status) {
        for (Status s : Status.values()) {
            if (s.name().equals(status.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
