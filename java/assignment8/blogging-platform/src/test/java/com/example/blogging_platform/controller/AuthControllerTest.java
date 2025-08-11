package com.example.blogging_platform.controller;

import com.example.bloggingplatform.controller.AuthController;
import com.example.bloggingplatform.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest {

    @Test
    void login_Success() throws Exception {
        AuthenticationManager authManager = mock(AuthenticationManager.class);
        JwtUtil jwtUtil = mock(JwtUtil.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);

        AuthController controller = new AuthController(authManager, jwtUtil, userDetailsService, encoder);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        User mockUser = new User("john", "pass", Collections.emptyList());
        when(userDetailsService.loadUserByUsername("john")).thenReturn(mockUser);
        when(jwtUtil.generateToken(mockUser)).thenReturn("fake-jwt");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john\",\"password\":\"pass\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("fake-jwt"));
    }

    @Test
    void login_InvalidCredentials() throws Exception {
        AuthenticationManager authManager = mock(AuthenticationManager.class);
        JwtUtil jwtUtil = mock(JwtUtil.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);

        doThrow(new BadCredentialsException("Bad creds"))
                .when(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        AuthController controller = new AuthController(authManager, jwtUtil, userDetailsService, encoder);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john\",\"password\":\"wrong\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid username or password"));
    }
}
