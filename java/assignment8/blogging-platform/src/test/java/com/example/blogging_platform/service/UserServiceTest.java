package com.example.blogging_platform.service;

import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.UserRepository;
import com.example.bloggingplatform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_Success() {
        User user = new User();
        user.setPassword("raw");

        when(encoder.encode("raw")).thenReturn("encoded");
        when(userRepository.save(user)).thenReturn(user);

        User saved = service.createUser(user);

        assertEquals("encoded", saved.getPassword());
        verify(userRepository).save(user);
    }
}
