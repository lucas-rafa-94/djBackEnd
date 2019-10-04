package com.umcincoum.dj.repository.postgre;

import com.umcincoum.dj.model.postgre.TbJukeboxArtist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TbJukeboxArtistRepository extends CrudRepository<TbJukeboxArtist, String> {
    List<TbJukeboxArtist> findByPlaylistOrderByName(String playlist);
    List<TbJukeboxArtist> findByPlaylistAndNameContains(String playlist, String name);
}
