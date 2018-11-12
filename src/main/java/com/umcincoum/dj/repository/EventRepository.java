package com.umcincoum.dj.repository;

import com.umcincoum.dj.model.mongoDb.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<EventModel, String> {
}
