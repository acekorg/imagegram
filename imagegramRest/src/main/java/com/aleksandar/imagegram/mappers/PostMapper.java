package com.aleksandar.imagegram.mappers;

import com.aleksandar.imagegram.api.model.Comment;
import com.aleksandar.imagegram.api.model.Post;
import com.aleksandar.imagegram.api.model.PostList;
import com.aleksandar.imagegram.model.CommentModel;
import com.aleksandar.imagegram.model.PostModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Post mapper.
 */
public final class PostMapper {

  /**
   * Map post business model to REST model.
   *
   * @param postModel post business model.
   * @return post REST model.
   */
  public static Post mapPostToRestModel(PostModel postModel) {

    Post post = new Post();

    String author = postModel.getAuthor();
    post.setAuthor(author);

    UUID id = postModel.getId();
    post.setId(id);

    String textCaption = postModel.getTextCaption();
    post.setTextCaption(textCaption);

    byte[] image = postModel.getImage();
    post.setImage(image);

    ArrayList<CommentModel> commentModels = postModel.getComments();

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
