package com.aleksandar.imagegram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Business model for posts.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostModel {

  private UUID id;
  private String author;
  private String textCaption;
  private byte[] image;
  private List<CommentModel> comments;

  public PostModel(String author, String textCaption, byte[] image) {
    this.author = author;
    this.textCaption = textCaption;
    this.image = image;
  }
}
