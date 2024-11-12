package com.meshal.taskenterdatabaseworld.repository;

import com.meshal.taskenterdatabaseworld.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
