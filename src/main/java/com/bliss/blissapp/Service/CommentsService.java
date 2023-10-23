package com.bliss.blissapp.Service;

import com.bliss.blissapp.Model.Comments;
import com.bliss.blissapp.Repository.CommentsRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentsService {
    private CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Optional<Comments> getCommentById(Long id) {
        return commentsRepository.findById(id);

    }
    public void createComment(Comments comment){
        commentsRepository.save(comment);
    }

    public void deleteCommentById(Long id){
        commentsRepository.deleteById(id);
    }
}
