package com.bliss.blissapp.Repository;

import com.bliss.blissapp.Model.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends MongoRepository<Posts, Long> {
}
