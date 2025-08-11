package com.example.blogging_platform.service;

import com.example.bloggingplatform.model.Post;
import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.PostRepository;
import com.example.bloggingplatform.repository.UserRepository;
import com.example.bloggingplatform.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost_Success() {
        User author = new User();
        author.setId(1L);

        Post post = new Post();
        post.setAuthor(author);

        when(userRepository.findById(1L)).thenReturn(Optional.of(author));
        when(postRepository.save(post)).thenReturn(post);

        Post result = service.createPost(post);

        assertEquals(author, result.getAuthor());
        verify(postRepository).save(post);
    }

    @Test
    void createPost_NoAuthor_Throws() {
        Post post = new Post();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.createPost(post));
        assertTrue(ex.getMessage().contains("Author information is missing"));
    }
}
