package com.bliss.blissapp.Controller;

import com.bliss.blissapp.DTO.CommentsDTO;
import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("/all")
    public ResponseEntity<List<CommentsDTO>> getAllComments() {
        return new ResponseEntity<>(commentsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentsDTO> createComment(@RequestBody Comments comment) {
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
    public ResponseEntity<List<CommentsDTO>> getCommentsByIds(@RequestParam List<UUID> ids) {
        List<CommentsDTO> comments = commentsService.findAllById(ids);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
