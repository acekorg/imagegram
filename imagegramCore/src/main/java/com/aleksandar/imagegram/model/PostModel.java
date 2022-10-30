package com.aleksandar.imagegram.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Business model for posts.
 */
public class PostModel {

  private UUID id;
  private String author;
  private String textCaption;

  private String imageName;
  private byte[] image;
  private ArrayList<CommentModel> comments;

  public PostModel(String author, String textCaption, byte[] image) {
    this.author = author;
    this.textCaption = textCaption;
    this.image = image;
  }

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

  public String getTextCaption() {
    return textCaption;
  }

  public void setTextCaption(String textCaption) {
    this.textCaption = textCaption;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public ArrayList<CommentModel> getComments() {
    return comments;
  }

  public void setComments(ArrayList<CommentModel> comments) {
    this.comments = comments;
  }

  public void addComment(CommentModel comment) {

    if (this.comments == null) {
      this.comments = new ArrayList<>();
    }

    this.comments.add(comment);
  }
}
