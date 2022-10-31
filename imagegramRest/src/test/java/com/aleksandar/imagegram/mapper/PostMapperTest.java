package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.api.model.Post;
import com.aleksandar.imagegram.api.model.PostList;
import com.aleksandar.imagegram.model.PostModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test mappers between comment business and REST model.
 */
@ExtendWith(MockitoExtension.class)
public class PostMapperTest {

  @Test
  public void shouldMapPostToRestModel() {

    // Given
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    PostModel postModel = PostModel.builder()
        .id(postId)
        .author("dummyAuthor")
        .textCaption("dummyText")
        .build();

    // When
    Post post = PostMapper.mapPostToRestModel(postModel);

    // Then
    assertThat(post.getId(), equalTo(postId));
    assertThat(post.getAuthor(), equalTo("dummyAuthor"));
    assertThat(post.getTextCaption(), equalTo("dummyText"));
    assertThat(post.getImageUrl(), equalTo("/api/imagegram/post/317192fd-3209-4024-b446-d57a107eb370/image"));
  }

  @Test
  public void shouldMapPostListToRestModel() {

    // Given
    UUID postId1 = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    PostModel postModel1 = PostModel.builder().id(postId1).build();

    UUID postId2 = UUID.fromString("317192fd-3209-4024-b446-d57a107eb371");
    PostModel postModel2 = PostModel.builder().id(postId2).build();

    List<PostModel> postModelList = List.of(postModel1, postModel2);

    // When
    PostList postListResponse = PostMapper.mapPostListToRestModel(postModelList);

    // Then
    List<Post> postList = postListResponse.getPostList();
    assertThat(postList.size(), equalTo(2));
  }


}
