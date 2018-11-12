package com.umcincoum.dj.repository;

import com.umcincoum.dj.model.mongoDb.UserModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

@Document
public interface UserRepository extends MongoRepository<UserModel, String> {
}