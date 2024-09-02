package com.bliss.blissapp.Service;

import com.bliss.blissapp.DTO.CommentsDTO;
import com.bliss.blissapp.DTO.PostsDTO;
import com.bliss.blissapp.Mapper.ContentMapper;
import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Model.Location;
import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Model.User;
import com.bliss.blissapp.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostsRepository postsRepository;
    private final UserService userService;
    private final CommentsService commentsService;
    private final ContentMapper contentMapper;

    public List<PostsDTO> getAllPosts() {
        return postsRepository.findAll().stream()
            .map(post -> contentMapper.toPostsDTO(post, commentsService.findAllById(post.getComments())))
            .collect(Collectors.toList());
    }

    public List<PostsDTO> getPostsByUserNearby(Location location) {
        List<String> usersNearby = this.userService.findUserNearby(location, 100)
            .stream().map(User::getUsername).toList();

        return postsRepository.findAll().stream()
            .filter(posts -> usersNearby.contains(posts.getAuthor()))
            .map(post -> contentMapper.toPostsDTO(post, commentsService.findAllById(post.getComments())))
            .collect(Collectors.toList());
    }

    public PostsDTO createPost(Posts post) {
        post.setId(UUID.randomUUID());
        Posts savedPost = postsRepository.save(post);
        return contentMapper.toPostsDTO(savedPost, commentsService.findAllById(post.getComments()));
    }

    public CommentsDTO addComment(UUID id, Comments comment) {
        return postsRepository.findById(id).map(post -> {
            commentsService.createComment(comment);
            post.getComments().add(comment.getId());
            postsRepository.save(post);
            return contentMapper.toCommentsDTO(comment, commentsService.findAllById(comment.getComments()));
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

}
