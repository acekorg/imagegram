package com.aleksandar.imagegram.model;

import java.util.UUID;

/**
 * Business model for comments.
 */
public class CommentModel {

  private UUID id;
  private String author;
  private String text;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
