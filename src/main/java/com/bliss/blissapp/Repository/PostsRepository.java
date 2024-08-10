package com.bliss.blissapp.Repository;

import com.bliss.blissapp.Model.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostsRepository extends MongoRepository<Posts, UUID> {
}
