package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.entity.CommentEntity;
import com.aleksandar.imagegram.model.CommentModel;

/**
 * Comment business domain mapper.
 */
public final class CommentBusinessMapper {

  /**
   * Convert comment entity to business model.
   * @param commentEntity comment entity.
   * @return comment business model.
   */
  public static CommentModel convertCommentEntityToBusinessModel(CommentEntity commentEntity) {
    return CommentModel.builder()
        .id(commentEntity.getId())
        .author(commentEntity.getAuthor())
        .text(commentEntity.getText())
        .build();
  }

  /**
   * Convert comment business to entity model.
   * @param commentModel comment business model.
   * @return comment entity model.
   */
  public static CommentEntity convertCommentBusinessModelToEntity(CommentModel commentModel) {
    return CommentEntity.builder()
        .id(commentModel.getId())
        .author(commentModel.getAuthor())
        .text(commentModel.getText())
        .build();
  }
}
