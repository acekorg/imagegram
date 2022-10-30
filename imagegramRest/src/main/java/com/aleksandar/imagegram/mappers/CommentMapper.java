package com.aleksandar.imagegram.mappers;

import com.aleksandar.imagegram.api.model.BaseComment;
import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.utils.AuthenticationUtils;

import java.util.UUID;

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

    Comment comment = new Comment();

    UUID id = commentModel.getId();
    comment.setId(id);

    String author = commentModel.getAuthor();
    comment.setAuthor(author);

    String text = commentModel.getText();
    comment.setText(text);

    return comment;
  }

  /**
   * Map comment REST model to business model.
   *
   * @param comment comment REST model.
   * @return comment business model.
   */
  public static CommentModel mapCommentToBusinessModel(BaseComment comment) {

    CommentModel commentModel = new CommentModel();

    String text = comment.getText();
    commentModel.setText(text);

    String loggedInUser = AuthenticationUtils.getLoggedInUser();
    commentModel.setAuthor(loggedInUser);

    return commentModel;
  }
}
