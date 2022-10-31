package com.aleksandar.imagegram.service;

import com.aleksandar.imagegram.entity.PostEntity;
import com.aleksandar.imagegram.model.PostModel;
import com.aleksandar.imagegram.repository.CommentRepository;
import com.aleksandar.imagegram.repository.PostRepository;
import com.aleksandar.imagegram.utils.MockDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test application flow in {@link PostService}
 */
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

  @Mock
  private PostRepository postRepository;

  @Mock
  private CommentRepository commentRepository;

  @InjectMocks
  private PostService postService;

  @Test
  public void shouldGetPosts() {

    // Given
    List<PostEntity> posts = MockDataGenerator.mockPostEntities();
    given(postRepository.getAllPosts()).willReturn(posts);

    // When
    postService.getPosts();

    // Then
    verify(postRepository, times(1)).getAllPosts();
    verify(commentRepository, times(posts.size())).getTwoLatestCommentsForPost(any());
  }

  @Test
  public void shouldCreatePost() {

    // Given
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    PostModel postModel = PostModel.builder().id(postId).build();
    PostEntity postEntity = MockDataGenerator.mockPostEntity(postId, "dummyUser", "dummyText");
    given(postRepository.addPost(any())).willReturn(postEntity);

    // When
    postService.createPost(postModel);

    // Then
    verify(postRepository, times(1)).addPost(any(PostEntity.class));
  }

  @Test
  public void shouldGetPostImage() {

    // Given
    UUID postId = UUID.fromString("317192fd-3209-4024-b446-d57a107eb370");
    PostEntity postEntity = PostEntity.builder().build();
    given(postRepository.getPostById(postId)).willReturn(postEntity);

    // When
    postService.getPostImage(postId);

    // Then
    verify(postRepository, times(1)).getPostById(postId);

  }
}
