package com.vikash.Journal.repository;

import com.vikash.Journal.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {


    User findByUsername(String username);
}
