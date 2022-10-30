package com.aleksandar.imagegram.service;

import com.aleksandar.imagegram.model.PostModel;
import com.aleksandar.imagegram.processor.ImageProcessor;
import com.aleksandar.imagegram.storage.PostStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business service for handling post operations.
 */
@Service
public class PostService {

  @Autowired
  private PostStorage postStorage;

  /**
   * Get list of all posts.
   *
   * @return All posts as a list.
   */
  public List<PostModel> getPosts() {
    return postStorage.getAllPosts();
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

    postStorage.addPost(postModel);
    return postModel;
  }

}
