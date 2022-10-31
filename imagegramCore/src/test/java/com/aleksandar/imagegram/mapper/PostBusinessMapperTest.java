package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.entity.PostEntity;
import com.aleksandar.imagegram.model.PostModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test for {@link PostBusinessMapper}
 */
@ExtendWith(MockitoExtension.class)
public class PostBusinessMapperTest {

  @Test
  public void shouldConvertPostEntityToBusinessModel() {

    // Given
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    byte[] dummyImage = hexStringToByteArray("124567890abcdef");
    PostEntity postEntity = PostEntity.builder()
        .id(postId)
        .textCaption("dummyText")
        .author("dummyUser")
        .image(dummyImage)
        .build();

    // When
    PostModel postModel = PostBusinessMapper.convertPostEntityToBusinessModel(postEntity);

    // Then
    assertThat(postModel.getId(), equalTo(postId));
    assertThat(postModel.getTextCaption(), equalTo("dummyText"));
    assertThat(postModel.getAuthor(), equalTo("dummyUser"));
    assertThat(postModel.getImage(), equalTo(dummyImage));
  }

  @Test
  public void shouldConvertPostBusinessModelToEntity(){

    // Given
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    byte[] dummyImage = hexStringToByteArray("124567890abcdef");

    PostModel postModel = PostModel.builder()
        .id(postId)
        .textCaption("dummyText")
        .author("dummyUser")
        .image(dummyImage)
        .build();

    // When
    PostEntity postEntity = PostBusinessMapper.convertPostBusinessModelToEntity(postModel);

    // Then
    assertThat(postEntity.getId(), equalTo(postId));
    assertThat(postEntity.getTextCaption(), equalTo("dummyText"));
    assertThat(postEntity.getAuthor(), equalTo("dummyUser"));
    assertThat(postEntity.getImage(), equalTo(dummyImage));
  }
}
