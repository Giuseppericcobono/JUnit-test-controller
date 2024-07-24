package com.example.test_controller.repositories;

import com.example.test_controller.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}