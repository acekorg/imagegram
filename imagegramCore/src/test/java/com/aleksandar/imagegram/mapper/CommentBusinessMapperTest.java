package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.entity.CommentEntity;
import com.aleksandar.imagegram.model.CommentModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test for {@link CommentBusinessMapper}
 */
@ExtendWith(MockitoExtension.class)
public class CommentBusinessMapperTest {

  @Test
  public void shouldConvertCommentEntityToBusinessModel(){

    // Given
    UUID commentId = UUID.fromString("f797d972-1f88-4812-b194-d9fb76513a6f");
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    OffsetDateTime timestamp = OffsetDateTime.now();

    CommentEntity commentEntity = CommentEntity.builder()
        .text("dummyText")
        .id(commentId)
        .author("dummyUser")
        .timestamp(timestamp)
        .postId(postId)
        .build();

    // When
    CommentModel commentModel = CommentBusinessMapper.convertCommentEntityToBusinessModel(commentEntity);

    // Then
    assertThat(commentModel.getId(), equalTo(commentId));
    assertThat(commentModel.getText(), equalTo("dummyText"));
    assertThat(commentModel.getAuthor(), equalTo("dummyUser"));
  }

  @Test
  public void shouldConvertCommentBusinessModelToEntity(){

    // Given
    UUID commentId = UUID.fromString("f797d972-1f88-4812-b194-d9fb76513a6f");
    CommentModel commentModel = CommentModel.builder()
        .id(commentId)
        .text("dummyText")
        .author("dummyUser")
        .build();

    // When
    CommentEntity commentEntity = CommentBusinessMapper.convertCommentBusinessModelToEntity(commentModel);

    // Then
    assertThat(commentEntity.getId(), equalTo(commentId));
    assertThat(commentEntity.getText(), equalTo("dummyText"));
    assertThat(commentEntity.getAuthor(), equalTo("dummyUser"));
  }

}
