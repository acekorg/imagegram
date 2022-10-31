package com.aleksandar.imagegram.service;

import com.aleksandar.imagegram.entity.CommentEntity;
import com.aleksandar.imagegram.entity.PostEntity;
import com.aleksandar.imagegram.mapper.CommentBusinessMapper;
import com.aleksandar.imagegram.mapper.PostBusinessMapper;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.model.PostModel;
import com.aleksandar.imagegram.processor.ImageProcessor;
import com.aleksandar.imagegram.repository.CommentRepository;
import com.aleksandar.imagegram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Business service for handling post operations.
 */
@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  /**
   * Get list of all posts.
   *
   * @return All posts as a list.
   */
  public List<PostModel> getPosts() {
    List<PostEntity> allPosts = postRepository.getAllPosts();

    List<PostModel> postModelList =
        allPosts.stream().map(post -> {

          PostModel postModel = PostBusinessMapper.convertPostEntityToBusinessModel(post);
          List<CommentEntity> comments = commentRepository.getTwoLatestCommentsForPost(postModel.getId());
          List<CommentModel> commentModelList =
              comments
                  .stream()
                  .map(CommentBusinessMapper::convertCommentEntityToBusinessModel)
                  .collect(Collectors.toList());

          postModel.setComments(commentModelList);

          return postModel;
        }).collect(Collectors.toList());
    return postModelList;
  }

  /**
   * Create new post
   *
   * @param postModel The post data that should to be stored.
   * @return The stored post model with the new ID.
   */
  public PostModel createPost(PostModel postModel) {

    byte[] providedImage = postModel.getImage();
    if (providedImage != null) {
      byte[] processedImage = ImageProcessor.process(providedImage);
      postModel.setImage(processedImage);
    }

    PostEntity postEntity = PostBusinessMapper.convertPostBusinessModelToEntity(postModel);
    PostEntity savedPostEntity = postRepository.addPost(postEntity);
    PostModel savedPostModel = PostBusinessMapper.convertPostEntityToBusinessModel(savedPostEntity);
    return savedPostModel;
  }

  /**
   * Get image for post.
   * @param postId The post id.
   * @return The image stored for the given post.
   */
  public byte[] getPostImage(UUID postId) {

    PostEntity post = postRepository.getPostById(postId);
    byte[] image = post.getImage();

    return image;
  }

}
