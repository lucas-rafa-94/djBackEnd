package com.umcincoum.dj.repository.mongo;

import com.umcincoum.dj.model.mongoDb.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<EventModel, String> {
    @Override
    List<EventModel> findAll();

    EventModel findByName(String id);

}
