package com.example.bloggingplatform.service;

import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    /**
     * Creates a new user.
     * @param user The user object to be created.
     * @return The saved user.
     */
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
