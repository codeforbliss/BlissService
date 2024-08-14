package com.bliss.blissapp.Service;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;

    public Comments getCommentById(UUID id) {
        return commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public List<Comments> findAllById(List<UUID> ids) {
        return commentsRepository.findAllById(ids);
    }

    public Comments createComment(Comments comment) {
        comment.setId(UUID.randomUUID());
        comment.setDate(Instant.now());
        return commentsRepository.save(comment);
    }

    public Comments addComment(UUID id, Comments comment) {
        return commentsRepository.findById(id).map(commentFound -> {
            Comments savedComment = createComment(comment);
            commentFound.getComments().add(savedComment.getId());
            commentsRepository.save(commentFound);
            return savedComment;
        }).orElseThrow(() -> new RuntimeException("Parent comment not found"));
    }

    public boolean deleteCommentById(UUID id) {
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
            deleteNestedComments(comment.getComments());
            commentsRepository.delete(comment);
            return true;
    }

    private void deleteNestedComments(List<UUID> commentIDs) {
        for (UUID id : commentIDs) {
            Optional<Comments> optionalComments = commentsRepository.findById(id);
            if (optionalComments.isPresent()) {
                Comments comments = optionalComments.get();
                deleteNestedComments(comments.getComments());
                commentsRepository.delete(comments);
            }
        }
    }
}
