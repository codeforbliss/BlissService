package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Model.Location;
import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Posts>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/posts-nearby")
    public ResponseEntity<List<Posts>> getPostsByUserNearby(@RequestBody Location location){
        return new ResponseEntity<>(postService.getPostsByUserNearby(location), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Posts> createPost(@RequestBody Posts post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> addComment(@PathVariable UUID id, @RequestBody Comments comment) {
        try {
            return new ResponseEntity<>(postService.addComment(id, comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
