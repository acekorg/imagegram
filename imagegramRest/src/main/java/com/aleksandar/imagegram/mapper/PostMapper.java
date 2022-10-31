package com.aleksandar.imagegram.mapper;

import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.api.model.Post;
import com.aleksandar.imagegram.api.model.PostList;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.model.PostModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Post mapper.
 */
public final class PostMapper {

  /**
   * Image url pattern for generating image URLs.
   */
  private static final String IMAGE_URL_PATTERN = "/api/imagegram/post/%s/image";

  /**
   * Map post business model to REST model.
   *
   * @param postModel post business model.
   * @return post REST model.
   */
  public static Post mapPostToRestModel(PostModel postModel) {

    UUID id = postModel.getId();
    Post post = new Post()
        .id(id)
        .author(postModel.getAuthor())
        .textCaption(postModel.getTextCaption())
        .imageUrl(String.format(IMAGE_URL_PATTERN, id));

    List<CommentModel> commentModels = postModel.getComments();

    if (commentModels != null) {
      List<Comment> comments =
          commentModels.stream().map(CommentMapper::mapCommentToRestModel).collect(Collectors.toList());

      post.setComments(comments);
    }

    return post;
  }

  /**
   * Map post business model list to list of REST models.
   *
   * @param postModels post business model list.
   * @return post REST model list
   */
  public static PostList mapPostListToRestModel(List<PostModel> postModels) {
    List<Post> posts = postModels.stream().map(PostMapper::mapPostToRestModel).collect(Collectors.toList());
    return new PostList().postList(posts);
  }
}
