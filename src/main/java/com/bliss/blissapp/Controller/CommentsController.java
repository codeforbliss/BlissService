package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping("/create/{entityId}")
    public void createComment(@RequestBody Comments comment, @PathVariable UUID entityId){
        comment.setId(UUID.randomUUID());
        comment.setEntityId(entityId);
        comment.setDate(Instant.now());
        commentsService.createComment(comment);
    }

    @GetMapping("/{id}")
    public Comments getCommentById(@PathVariable UUID id){
        return commentsService.getCommentById(id);
    }

    @GetMapping("/entity/{entityId}")
    public List<Comments> findCommentsByEntityId(@PathVariable UUID entityId) {
        return commentsService.getAllCommentsByEntityId(entityId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommentById(UUID id) {
        commentsService.deleteCommentById(id);
    }
}
