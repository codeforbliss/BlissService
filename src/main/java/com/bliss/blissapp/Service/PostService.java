package com.bliss.blissapp.Service;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Model.Location;
import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Model.User;
import com.bliss.blissapp.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final PostsRepository postsRepository;
    private final UserService userService;

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

    public void createPost(Posts post){
        postsRepository.save(post);
    }

    public void addComment(UUID postId, UUID commentId) {
        Posts post = postsRepository.findById(postId, Posts.class);
        post.getCommentIds().add(commentId);
        post.setCommentIds(post.getCommentIds());
        postsRepository.save(post);
    }

    public List<Comments> getCommentsByPostId(UUID postId) {
        // Get the post by ID to fetch its comment IDs
        Posts post = postsRepository.findById(postId, Posts.class);
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }

        // Aggregation to match comments with the IDs in the post's comments list
        Aggregation aggregation = newAggregation(
                match(new org.springframework.data.mongodb.core.query.Criteria("_id").in(post.getCommentIds()))
        );

        AggregationResults<Comments> results = mongoTemplate.aggregate(aggregation, "comments", Comments.class);
        return results.getMappedResults();
    }

}
