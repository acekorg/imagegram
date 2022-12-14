package com.aleksandar.imagegram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Business model for comments.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentModel {

  private UUID id;
  private String author;
  private String text;
}
