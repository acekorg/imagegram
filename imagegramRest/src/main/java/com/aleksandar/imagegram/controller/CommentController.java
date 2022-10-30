package com.aleksandar.imagegram.controller;

import com.aleksandar.imagegram.api.CommentApi;
import com.aleksandar.imagegram.api.model.BaseComment;
import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.mappers.CommentMapper;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommentController implements CommentApi {

  @Autowired
  private CommentService commentService;

  @Override
  public ResponseEntity<Comment> addComment(UUID postId, BaseComment baseComment) {
    CommentModel commentModel = CommentMapper.mapCommentToBusinessModel(baseComment);
    commentService.addComment(postId, commentModel);
    // TODO: 29.10.22 Do we need the comment back?

    return ResponseEntity.ok(null);
  }

  @Override
  public ResponseEntity<Void> removeComment(UUID postId, UUID commentId) {
    commentService.removeComment(postId, commentId);
    return ResponseEntity.ok().build();
  }
}
