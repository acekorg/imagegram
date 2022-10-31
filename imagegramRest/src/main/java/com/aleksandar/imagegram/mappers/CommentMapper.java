package com.aleksandar.imagegram.mappers;

import com.aleksandar.imagegram.api.model.NewCommentRequest;
import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.utils.AuthenticationUtils;

/**
 * Comment mapper.
 */
public final class CommentMapper {

  /**
   * Map comment business model to REST model.
   *
   * @param commentModel comment business model.
   * @return comment REST model.
   */
  public static Comment mapCommentToRestModel(CommentModel commentModel) {

    return new Comment()
        .id(commentModel.getId())
        .author(commentModel.getAuthor())
        .text(commentModel.getText());
  }

  /**
   * Map comment REST model to business model.
   *
   * @param comment comment REST model.
   * @return comment business model.
   */
  public static CommentModel mapCommentToBusinessModel(NewCommentRequest comment) {

    String loggedInUser = AuthenticationUtils.getLoggedInUser();

    return CommentModel.builder()
        .text(comment.getText())
        .author(loggedInUser)
        .build();
  }
}
