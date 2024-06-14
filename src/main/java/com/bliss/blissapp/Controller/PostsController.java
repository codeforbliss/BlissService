package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.Posts;
import com.bliss.blissapp.Service.NextSequenceService;
import com.bliss.blissapp.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@CrossOrigin
public class PostsController {
    private final PostService postService;
    private final NextSequenceService nextSequenceService;

    @GetMapping("/all")
    public List<Posts> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/")
    public void createPost(@RequestBody Posts post){
        post.setId(nextSequenceService.getNextSequence("customSequences"));
        postService.createPost(post);
    }

}
