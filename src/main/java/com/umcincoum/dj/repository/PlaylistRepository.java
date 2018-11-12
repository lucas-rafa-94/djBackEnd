package com.umcincoum.dj.repository;

import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends MongoRepository<PlaylistModel, String> {
}
