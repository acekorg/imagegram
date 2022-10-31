package com.aleksandar.imagegram.controller;

import com.aleksandar.imagegram.api.PostApi;
import com.aleksandar.imagegram.api.model.Post;
import com.aleksandar.imagegram.api.model.PostList;
import com.aleksandar.imagegram.mapper.PostMapper;
import com.aleksandar.imagegram.model.PostModel;
import com.aleksandar.imagegram.service.PostService;
import com.aleksandar.imagegram.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

  private final PostService postService;

  @Override
  public ResponseEntity<PostList> getPosts() {
    List<PostModel> posts = postService.getPosts();
    PostList postList = PostMapper.mapPostListToRestModel(posts);
    return ResponseEntity.ok(postList);
  }

  @Override
  public ResponseEntity<Post> createPost(String textCaption, MultipartFile image) {

    try {
      byte[] imageData = image.getBytes();
      String loggedInUser = AuthenticationUtils.getLoggedInUser();
      PostModel postModel = new PostModel(loggedInUser, textCaption, imageData);
      PostModel savedPost = postService.createPost(postModel);

      Post post = PostMapper.mapPostToRestModel(savedPost);
      return ResponseEntity.ok(post);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Resource> getPostImage(UUID postId) {
    byte[] postImage = postService.getPostImage(postId);
    ByteArrayResource imageResource = new ByteArrayResource(postImage);
    return ResponseEntity.ok(imageResource);
  }
}
