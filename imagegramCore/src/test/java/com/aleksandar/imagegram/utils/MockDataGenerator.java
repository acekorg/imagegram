package com.aleksandar.imagegram.utils;

import com.aleksandar.imagegram.entity.PostEntity;

import java.util.List;
import java.util.UUID;

/**
 * Generator of mock data for test purposes.
 */
public final class MockDataGenerator {

  /**
   * Mock post entities.
   * @return List of dummy post entities.
   */
  public static List<PostEntity> mockPostEntities() {
    return List.of(
        mockPostEntity(UUID.randomUUID(), "dummyUser1", "firstPost"),
        mockPostEntity(UUID.randomUUID(), "dummyUser2", "secondPost")
    );
  }

  /**
   * Mock single post entity.
   * @param id id of the post.
   * @param author author of the post.
   * @param text text of the post.
   * @return the created post.
   */
  public static PostEntity mockPostEntity(UUID id, String author, String text) {
    return PostEntity.builder()
        .id(id)
        .author(author)
        .textCaption(text)
        .build();
  }

}
