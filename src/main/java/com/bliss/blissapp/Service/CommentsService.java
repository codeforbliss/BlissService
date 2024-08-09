package com.bliss.blissapp.Service;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final MongoTemplate mongoTemplate;
    private final CommentsRepository commentsRepository;

    public Comments getCommentById(UUID id) {
        return commentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public void createComment(Comments comment){
        commentsRepository.save(comment);
    }

    public void deleteCommentById(UUID id){
        commentsRepository.deleteById(id);
    }

    public List<Comments> getAllCommentsByEntityId(UUID entityId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("entityId").is(entityId));
        return mongoTemplate.find(query, Comments.class);
    }
}
