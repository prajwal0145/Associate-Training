package com.example.bloggingplatform.repository;

import com.example.bloggingplatform.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // You can add custom query methods here if needed
}
