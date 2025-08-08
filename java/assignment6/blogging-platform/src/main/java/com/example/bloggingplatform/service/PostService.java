package com.example.bloggingplatform.service;

import com.example.bloggingplatform.model.Post;
import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.PostRepository;
import com.example.bloggingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new blog post.
     * @param post The post to be created.
     * @return The saved post.
     */
    @Transactional
    public Post createPost(Post post) {
        // First, check if the author object and its ID are present
        if (post.getAuthor() == null || post.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Author information is missing in the post.");
        }

        // Fetch the full, managed User entity from the database
        User author = userRepository.findById(post.getAuthor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + post.getAuthor().getId()));

        // --- THIS IS THE FIX ---
        // Set the complete author object on the post before saving.
        post.setAuthor(author);

        // Now, save the post. The returned object will have the full author details.
        return postRepository.save(post);
    }

    /**
     * Retrieves all posts with optional filtering by author and pagination.
     * @param authorId Optional author ID to filter by.
     * @param pageable Pagination information.
     * @return A page of posts.
     */
    public Page<Post> getAllPosts(Optional<Long> authorId, Pageable pageable) {
        if (authorId.isPresent()) {
            return postRepository.findByAuthorId(authorId.get(), pageable);
        } else {
            return postRepository.findAll(pageable);
        }
    }

    /**
     * Retrieves a single post by its ID.
     * @param id The ID of the post.
     * @return An Optional containing the post if found.
     */
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}
