package com.aleksandar.imagegram.repository;

import com.aleksandar.imagegram.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Comment repository for CRUD operations with comment data.
 * TODO The comment table is in-memory simulation of a database. It should be replaced with real database connection.
 */
@Component
public class CommentRepository {

  private static HashMap<UUID, CommentEntity> commentTable = new HashMap<>();

  /**
   * Get comment data by id.
   * @param id The comment id.
   * @return The comment data.
   */
  public CommentEntity getCommentById(UUID id) {
    return commentTable.get(id);
  }

  /**
   * Get comment list for certain post.
   * @param postId The post id.
   * @return The comment list.
   */
  public List<CommentEntity> getCommentsForPost(UUID postId) {
    List<CommentEntity> collect =
        commentTable
            .entrySet()
            .stream().filter(entry -> entry.getValue().getPostId().equals(postId))
            .map(entry -> entry.getValue())
            .collect(Collectors.toList());

    return collect;
  }

  /**
   * Get the two latest comment list for certain post.
   * @param postId The post id.
   * @return The comment list.
   */
  public List<CommentEntity> getTwoLatestCommentsForPost(UUID postId) {
    List<CommentEntity> collect =
        commentTable
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue().getPostId().equals(postId))
            .map(entry -> entry.getValue())
            .sorted(Comparator.comparing(CommentEntity::getOffsetDateTime).reversed())
            .limit(2)
            .collect(Collectors.toList());

    return collect;
  }


  /**
   * Add new comment.
   * @param commentEntity The new comment.
   * @return The comment saved with persistent unique ID.
   */
  public CommentEntity addComment(CommentEntity commentEntity) {
    UUID id = UUID.randomUUID();
    commentEntity.setId(id);
    commentTable.put(id, commentEntity);
    return commentEntity;
  }

  /**
   * Remove comment from post.
   * @param commentId The comment id.
   */
  public void removeCommentFromPost(UUID commentId) {
    commentTable.remove(commentId);
  }

}
