package com.aleksandar.imagegram.repository;

import com.aleksandar.imagegram.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Post repository for CRUD operations with post data.
 * TODO The post table is in-memory simulation of a database. It should be replaced with real database connection.
 */
@Component
public class PostRepository {

  private static HashMap<UUID, PostEntity> postTable = new HashMap<>();

  /**
   * Get all posts.
   */
  public List<PostEntity> getAllPosts() {
    return new ArrayList<>(postTable.values());
  }

  /**
   * Get certain post by id.
   * @param id The post id.
   * @return The post data.
   */
  public PostEntity getPostById(UUID id) {
    return postTable.get(id);
  }

  /**
   * Add new post.
   * @param postEntity The new post.
   * @return The post saved with persistent unique ID.
   */
  public PostEntity addPost(PostEntity postEntity) {
    UUID id = UUID.randomUUID();
    postEntity.setId(id);
    postTable.put(id, postEntity);
    return postEntity;
  }

}
