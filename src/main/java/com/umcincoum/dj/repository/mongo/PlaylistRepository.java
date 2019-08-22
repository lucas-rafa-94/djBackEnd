package com.umcincoum.dj.repository.mongo;

import com.umcincoum.dj.model.mongoDb.PlaylistModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends MongoRepository<PlaylistModel, String> {
    List<PlaylistModel> findByEventModel_Id(String event);
    PlaylistModel findByPlaylist(String playlist);
}
