package com.example.blogging_platform.controller;

import com.example.bloggingplatform.controller.CommentController;
import com.example.bloggingplatform.model.Comment;
import com.example.bloggingplatform.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createComment_Success() {
        Comment comment = new Comment();
        when(commentService.createComment(1L, comment)).thenReturn(comment);

        ResponseEntity<?> response = controller.createComment(1L, comment);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(comment, response.getBody());
    }

    @Test
    void createComment_BadRequest() {
        Comment comment = new Comment();
        when(commentService.createComment(1L, comment)).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = controller.createComment(1L, comment);

        assertEquals(400, response.getStatusCodeValue());
    }
}
