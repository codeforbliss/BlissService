package com.bliss.blissapp.Service;

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

    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    public List<Posts> getPostsByUserNearby(Location location) {
        List<String> usersNearby = this.userService.findUserNearby(location, 100)
            .stream().map(User::getUsername).toList();

        return postsRepository.findAll().stream()
            .filter(posts -> usersNearby.contains(posts.getAuthor()))
            .collect(Collectors.toList());
    }

    public Posts createPost(Posts post) {
        post.setId(UUID.randomUUID());
        return postsRepository.save(post);
    }

    public Comments addComment(UUID id, Comments comment) {
        return postsRepository.findById(id).map(post -> {
            commentsService.createComment(comment);
            post.getComments().add(comment.getId());
            postsRepository.save(post);
            return comment;
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

}
