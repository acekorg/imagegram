package com.aleksandar.imagegram.service;

import com.aleksandar.imagegram.entity.CommentEntity;
import com.aleksandar.imagegram.mapper.CommentBusinessMapper;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.repository.CommentRepository;
import com.aleksandar.imagegram.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Business service for handling comment operations.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  /**
   * Add new comment.
   *
   * @param postId       The postId to which the comment is attached.
   * @param commentModel The comment data that should to be stored.
   * @return The stored comment model with the new ID.
   */
  public CommentModel addComment(UUID postId, CommentModel commentModel) {

    CommentEntity commentEntity = CommentBusinessMapper.convertCommentBusinessModelToEntity(commentModel);

    String loggedInUser = AuthenticationUtils.getLoggedInUser();
    commentEntity.setAuthor(loggedInUser);
    commentEntity.setPostId(postId);
    commentEntity.setTimestamp(OffsetDateTime.now());

    CommentEntity savedCommentEntity = commentRepository.addComment(commentEntity);
    CommentModel savedCommentModel = CommentBusinessMapper.convertCommentEntityToBusinessModel(savedCommentEntity);
    return savedCommentModel;
  }

  /**
   * Remove comment from post.
   * @param commentId The comment id.
   * @return status if the comment is removed.
   */
  public boolean removeComment(UUID commentId) {

    String loggedInUser = AuthenticationUtils.getLoggedInUser();
    CommentEntity commentEntity = commentRepository.getCommentById(commentId);

    if (commentEntity != null && commentEntity.getAuthor().equals(loggedInUser)) {
      commentRepository.removeCommentFromPost(commentId);
      return true;
    } else {
      return false;
    }
  }
}
