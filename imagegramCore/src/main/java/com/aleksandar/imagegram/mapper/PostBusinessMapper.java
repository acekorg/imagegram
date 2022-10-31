package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.entity.PostEntity;
import com.aleksandar.imagegram.model.PostModel;

/**
 * Post business domain mapper.
 */
public final class PostBusinessMapper {

  /**
   * Convert post entity to business model.
   * @param postEntity post entity.
   * @return post business model.
   */
  public static PostModel convertPostEntityToBusinessModel(PostEntity postEntity) {
    return PostModel.builder()
        .id(postEntity.getId())
        .author(postEntity.getAuthor())
        .textCaption(postEntity.getTextCaption())
        .image(postEntity.getImage())
        .build();
  }

  /**
   * Convert post business to entity model.
   * @param postModel post business model.
   * @return post entity model.
   */
  public static PostEntity convertPostBusinessModelToEntity(PostModel postModel) {
    return PostEntity.builder()
        .id(postModel.getId())
        .author(postModel.getAuthor())
        .textCaption(postModel.getTextCaption())
        .image(postModel.getImage())
        .build();
  }
}
