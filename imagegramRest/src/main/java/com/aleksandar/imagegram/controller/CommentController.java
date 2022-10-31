package com.aleksandar.imagegram.controller;

import com.aleksandar.imagegram.api.CommentApi;
import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.api.model.NewCommentRequest;
import com.aleksandar.imagegram.mapper.CommentMapper;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {

  private final CommentService commentService;

  @Override
  public ResponseEntity<Comment> addComment(UUID postId, NewCommentRequest newCommentRequest) {
    CommentModel commentModel = CommentMapper.mapCommentToBusinessModel(newCommentRequest);
    CommentModel savedComment = commentService.addComment(postId, commentModel);
    Comment comment = CommentMapper.mapCommentToRestModel(savedComment);
    return ResponseEntity.ok(comment);
  }

  @Override
  public ResponseEntity<Void> removeComment(UUID commentId) {

    boolean sucessStatus = commentService.removeComment(commentId);

    if (sucessStatus) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
  }
}
