package com.example.bloggingplatform.controller;

import com.example.bloggingplatform.model.Comment;
import com.example.bloggingplatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts") // Note the URL structure
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Endpoint to create a new comment on a specific post.
     * @param postId The ID of the post from the URL path.
     * @param comment The comment data from the request body.
     * @return The created comment with an HTTP 201 status.
     */
    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestParam Long postId, @RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.createComment(postId, comment);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handles cases where post or author is not found
            return new ResponseEntity<>("not founddddddddddddddddddddddddddddddd", HttpStatus.BAD_REQUEST);
        }
    }
}
