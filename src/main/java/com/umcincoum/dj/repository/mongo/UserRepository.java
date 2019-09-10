package com.umcincoum.dj.repository.mongo;

import com.umcincoum.dj.model.mongoDb.UserModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

@Document
public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findByEmailAndAndPassword(String email, String password);
    UserModel findByEmail(String email);

}
