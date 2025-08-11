package com.example.blogging_platform.controller;

import com.example.bloggingplatform.controller.PostController;
import com.example.bloggingplatform.model.Post;
import com.example.bloggingplatform.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost_Success() {
        Post post = new Post();
        when(postService.createPost(post)).thenReturn(post);

        ResponseEntity<Post> response = controller.createPost(post);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(post, response.getBody());
    }

    @Test
    void getAllPosts_Success() {
        Page<Post> page = new PageImpl<>(List.of(new Post()));

        when(postService.getAllPosts(any(Optional.class), any(Pageable.class)))
                .thenReturn(page);

        ResponseEntity<Page<Post>> response = controller.getAllPosts(Optional.empty(), 0, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
    }


    @Test
    void getPostById_Found() {
        Post post = new Post();
        when(postService.getPostById(1L)).thenReturn(Optional.of(post));

        ResponseEntity<Post> response = controller.getPostById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(post, response.getBody());
    }

    @Test
    void getPostById_NotFound() {
        when(postService.getPostById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Post> response = controller.getPostById(1L);

        assertEquals(404, response.getStatusCodeValue());
    }
}
