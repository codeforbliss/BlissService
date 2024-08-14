package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("/all")
    public ResponseEntity<List<Comments>> getAllComments() {
        return new ResponseEntity<>(commentsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
        return new ResponseEntity<>(commentsService.createComment(comment), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(commentsService.getCommentById(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(commentsService.deleteCommentById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> addComment(@RequestBody Comments comment, @PathVariable UUID id) {
        try {
            return new ResponseEntity<>(commentsService.addComment(id, comment), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/batch")
    public ResponseEntity<List<Comments>> getCommentsByIds(@RequestBody List<UUID> ids) {
        List<Comments> comments = commentsService.findAllById(ids);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
