package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.api.model.NewCommentRequest;
import com.aleksandar.imagegram.model.CommentModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test mappers between comment business and REST model.
 */
@ExtendWith(MockitoExtension.class)
public class CommentMapperTest {

  @Test
  public void shouldMapCommentToBusinessModel() {

    // Given
    NewCommentRequest newCommentRequest = new NewCommentRequest().text("dummyText");

    // When
    CommentModel commentModel = CommentMapper.mapCommentToBusinessModel(newCommentRequest);

    // Then
    assertThat(commentModel.getText(), equalTo("dummyText"));
  }

  @Test
  public void shouldMapCommentToRestModel() {

    // Given
    UUID commentId = UUID.fromString("f797d972-1f88-4812-b194-d9fb76513a6f");
    CommentModel commentModel = CommentModel.builder()
        .id(commentId)
        .author("dummyUser")
        .text("dummyText")
        .build();

    // When
    Comment comment = CommentMapper.mapCommentToRestModel(commentModel);

    // Then
    assertThat(comment.getId(), equalTo(commentId));
    assertThat(comment.getAuthor(), equalTo("dummyUser"));
    assertThat(comment.getText(), equalTo("dummyText"));
  }
}
