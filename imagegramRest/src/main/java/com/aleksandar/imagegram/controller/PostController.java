package com.aleksandar.imagegram.controller;

import com.aleksandar.imagegram.api.PostApi;
import com.aleksandar.imagegram.api.model.Post;
import com.aleksandar.imagegram.api.model.PostList;
import com.aleksandar.imagegram.mappers.PostMapper;
import com.aleksandar.imagegram.model.PostModel;
import com.aleksandar.imagegram.service.PostService;
import com.aleksandar.imagegram.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController implements PostApi {

  @Autowired
  private PostService postService;

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
      PostModel post = postService.createPost(postModel);
      // TODO: 29.10.22 Do we need to return Post model ?
      return ResponseEntity.ok(new Post());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
