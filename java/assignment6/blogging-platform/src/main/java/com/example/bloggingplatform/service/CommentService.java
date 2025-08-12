package com.example.bloggingplatform.service;

import com.example.bloggingplatform.model.Comment;
import com.example.bloggingplatform.model.Post;
import com.example.bloggingplatform.model.User;
import com.example.bloggingplatform.repository.CommentRepository;
import com.example.bloggingplatform.repository.PostRepository;
import com.example.bloggingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new comment and associates it with a post and an author.
     * @param postId The ID of the post to comment on.
     * @param comment The comment object from the request.
     * @return The saved comment with full author and post details.
     */
    @Transactional
    public Comment createComment(Long postId, Comment comment) {
        // Ensure the author information is provided
        if (comment.getAuthor() == null || comment.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Comment must have an author.");
        }

        // Find the post that this comment belongs to
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));

        // Find the user who is writing the comment
        User author = userRepository.findById(comment.getAuthor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + comment.getAuthor().getId()));

        // Set the associations
        comment.setPost(post);
        comment.setAuthor(author);

        // Save the new comment
        return commentRepository.save(comment);
    }
}
