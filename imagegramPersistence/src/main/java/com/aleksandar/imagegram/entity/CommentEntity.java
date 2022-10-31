package com.aleksandar.imagegram.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Comment entity.
 */
@Getter
@Setter
@Builder
public class CommentEntity {

  private UUID id;
  private String author;
  private String text;
  private OffsetDateTime offsetDateTime;
  private UUID postId;

}
