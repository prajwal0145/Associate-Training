package com.example.bloggingplatform.repository;

import com.example.bloggingplatform.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Finds a paginated list of posts by a specific author.
     * @param authorId The ID of the author.
     * @param pageable Pagination information.
     * @return A page of posts.
     */
    Page<Post> findByAuthorId(Long authorId, Pageable pageable);
}
