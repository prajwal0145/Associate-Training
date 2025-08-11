package com.example.blogging_platform.service;

import com.example.bloggingplatform.model.Comment;
import com.example.bloggingplatform.model.Post;
import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.CommentRepository;
import com.example.bloggingplatform.repository.PostRepository;
import com.example.bloggingplatform.repository.UserRepository;
import com.example.bloggingplatform.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createComment_Success() {
        User author = new User();
        author.setId(1L);
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();
        comment.setAuthor(author);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(userRepository.findById(1L)).thenReturn(Optional.of(author));
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment result = service.createComment(1L, comment);

        assertEquals(post, result.getPost());
        assertEquals(author, result.getAuthor());
    }

    @Test
    void createComment_NoAuthor_Throws() {
        Comment comment = new Comment();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.createComment(1L, comment));
        assertTrue(ex.getMessage().contains("Comment must have an author"));
    }
}
