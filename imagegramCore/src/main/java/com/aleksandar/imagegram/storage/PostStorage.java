package com.aleksandar.imagegram.storage;

import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.model.PostModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Quick in-memory storage implementation.
 */
@Component
public class PostStorage {

  public PostStorage() {

  }

  private static HashMap<UUID, PostModel> postTable = new HashMap<>();

  public List<PostModel> getAllPosts() {
    return new ArrayList<>(postTable.values());
  }

  public PostModel getPostById(UUID id) {
    return postTable.get(id);
  }

  public void addPost(PostModel postModel) {

    UUID id = UUID.randomUUID();
    postModel.setId(id);
    postTable.put(id, postModel);
  }

  public void addCommentToPost(UUID postId, CommentModel commentModel) {
    PostModel postModel = postTable.get(postId);
    postModel.addComment(commentModel);
  }

  public void removeCommentFromPost(UUID postId, UUID commentId) {
    PostModel postModel = postTable.get(postId);
    ArrayList<CommentModel> comments = postModel.getComments();
    comments.removeIf(comment -> comment.getId().equals(commentId));
  }
}
