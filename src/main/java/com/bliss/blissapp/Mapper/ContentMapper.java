package com.bliss.blissapp.Mapper;
import com.bliss.blissapp.DTO.CommentsDTO;
import com.bliss.blissapp.DTO.PostsDTO;
import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Repository.CommentsRepository;
import com.bliss.blissapp.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContentMapper {
  public PostsDTO toPostsDTO(Posts posts, List<CommentsDTO> commentsDTOList) {
    PostsDTO postsDTO = new PostsDTO();
    postsDTO.setId(posts.getId());
    postsDTO.setText(posts.getText());
    postsDTO.setAuthor(posts.getAuthor());
    postsDTO.setDate(posts.getPostDate());
    postsDTO.setComments(commentsDTOList);

    return postsDTO;
  }

  public CommentsDTO toCommentsDTO(Comments comments, List<CommentsDTO> commentsDTOList) {
    CommentsDTO commentsDTO = new CommentsDTO();
    commentsDTO.setId(comments.getId());
    commentsDTO.setText(comments.getText());
    commentsDTO.setAuthor(comments.getAuthor());
    commentsDTO.setDate(comments.getDate());
    commentsDTO.setComments(commentsDTOList);

    return commentsDTO;
  }

}
