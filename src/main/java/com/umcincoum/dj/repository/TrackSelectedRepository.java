package com.umcincoum.dj.repository;

import com.umcincoum.dj.model.mongoDb.TrackSelectedModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackSelectedRepository extends MongoRepository<TrackSelectedModel, String> {
}
