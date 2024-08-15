package com.bliss.blissapp.Service;

import com.bliss.blissapp.DTO.CommentsDTO;
import com.bliss.blissapp.Mapper.ContentMapper;
import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final ContentMapper contentMapper;

    public List<CommentsDTO> findAll() {
        return commentsRepository.findAll().stream()
            .map(comment -> contentMapper.toCommentsDTO(comment, findAllById(comment.getComments())))
            .collect(Collectors.toList());
    }

    public CommentsDTO getCommentById(UUID id) {
        Comments comment = commentsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
        return contentMapper.toCommentsDTO(comment, findAllById(comment.getComments()));
    }

    public List<CommentsDTO> findAllById(List<UUID> ids) {
        return commentsRepository.findAllById(ids).stream()
            .map(comment -> contentMapper.toCommentsDTO(comment, findAllById(comment.getComments())))
            .collect(Collectors.toList());
    }

    public CommentsDTO createComment(Comments comment) {
        comment.setId(UUID.randomUUID());
        comment.setDate(Instant.now());
        Comments savedComment = commentsRepository.save(comment);
        return contentMapper.toCommentsDTO(savedComment, findAllById(comment.getComments()));
    }

    public CommentsDTO addComment(UUID id, Comments comment) {
        return commentsRepository.findById(id).map(commentFound -> {
            CommentsDTO savedComment = this.createComment(comment);
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
