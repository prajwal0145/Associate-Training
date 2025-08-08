package com.example.bloggingplatform.service;

import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user.
     * @param user The user object to be created.
     * @return The saved user.
     */
    public User createUser(User user) {
        // In a real application, you would add password encoding and validation here
        return userRepository.save(user);
    }
}