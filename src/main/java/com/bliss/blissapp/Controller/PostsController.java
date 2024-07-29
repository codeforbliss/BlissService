package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.Location;
import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin
public class PostsController {
    private final PostService postService;

    @GetMapping("/all")
    public List<Posts> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/posts-nearby")
    public List<Posts> getPostsByUserNearby(@RequestBody Location location){
        return postService.getPostsByUserNearby(location);
    }

    @PostMapping("/create")
    public void createPost(@RequestBody Posts post){
        post.setId(UUID.randomUUID());
        postService.createPost(post);
    }

}
