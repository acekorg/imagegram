package com.aleksandar.imagegram.service;

import com.aleksandar.imagegram.utils.AuthenticationTestUtils;
import com.aleksandar.imagegram.entity.CommentEntity;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Test application flow in {@link CommentService}
 */
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

  @Mock
  private CommentRepository commentRepository;

  @InjectMocks
  private CommentService commentService;

  @Test
  public void shouldAddComment() {

    // Given
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    UUID commentId = UUID.fromString("f797d972-1f88-4812-b194-d9fb76513a6f");
    AuthenticationTestUtils.mockSecurityContext();
    CommentEntity comment = CommentEntity.builder().id(commentId).build();
    given(commentRepository.addComment(any())).willReturn(comment);
    CommentModel commentModel = CommentModel.builder()
        .id(commentId)
        .text("dummyText")
        .author("dummyUser")
        .build();

    // When
    commentService.addComment(postId, commentModel);

    // Then
    verify(commentRepository, times(1)).addComment(any());
  }

  @Test
  public void shouldRemoveComment() {

    // Given
    UUID commentId = UUID.fromString("f797d972-1f88-4812-b194-d9fb76513a6f");
    AuthenticationTestUtils.mockSecurityContext();
    CommentEntity comment = CommentEntity.builder().author("dummyUser").build();
    given(commentRepository.getCommentById(commentId)).willReturn(comment);

    // When
    commentService.removeComment(commentId);

    // Then
    verify(commentRepository, times(1)).getCommentById(commentId);
    verify(commentRepository, times(1)).removeCommentFromPost(commentId);
  }
}
