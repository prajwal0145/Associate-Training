package com.example.bloggingplatform.controller;

import com.example.bloggingplatform.model.Post;
import com.example.bloggingplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Endpoint to create a new blog post.
     * @param post The post data from the request body.
     * @return The created post with an HTTP 201 status.
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postService.createPost(post);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle cases where the author does not exist or other bad requests
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint to get all posts with pagination and filtering by author.
     * @param authorId Optional request parameter to filter posts by author.
     * @param page Page number for pagination (default is 0).
     * @param size Number of items per page (default is 10).
     * @return A ResponseEntity containing a page of posts.
     */
    @GetMapping
    public ResponseEntity<Page<Post>> getAllPosts(
            @RequestParam Optional<Long> authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Create Pageable object for pagination and sorting by creation date descending
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Post> posts = postService.getAllPosts(authorId, pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Endpoint to get a single post by its ID.
     * @param id The ID of the post.
     * @return A ResponseEntity containing the post or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
