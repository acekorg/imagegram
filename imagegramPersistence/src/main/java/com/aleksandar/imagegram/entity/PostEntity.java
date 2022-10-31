package com.aleksandar.imagegram.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Post entity.
 */
@Getter
@Setter
@Builder
public class PostEntity {

  private UUID id;
  private String author;
  private String textCaption;
  private byte[] image;

}
