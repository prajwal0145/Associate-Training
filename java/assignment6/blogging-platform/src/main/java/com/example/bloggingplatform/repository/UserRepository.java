package com.example.bloggingplatform.repository;

import com.example.bloggingplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides CRUD methods like save(), findById(), findAll(), etc.
}
