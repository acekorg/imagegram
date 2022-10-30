package com.aleksandar.imagegram.service;

import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.storage.PostStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Business service for handling comment operations.
 */
@Service
public class CommentService {

  @Autowired
  private PostStorage postStorage;

  /**
   * Add new comment.
   *
   * @param postId       The postId to which the comment is attached.
   * @param commentModel The comment data that should to be stored.
   * @return The stored comment model with the new ID.
   */
  public CommentModel addComment(UUID postId, CommentModel commentModel) {

    postStorage.addCommentToPost(postId, commentModel);
    return commentModel;
  }


  public void removeComment(UUID postId, UUID commentId) {
    postStorage.removeCommentFromPost(postId, commentId);
  }
}
