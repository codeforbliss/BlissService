package com.bliss.blissapp.Repository;

import com.bliss.blissapp.Model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentsRepository extends MongoRepository<Comments, UUID> {
}
