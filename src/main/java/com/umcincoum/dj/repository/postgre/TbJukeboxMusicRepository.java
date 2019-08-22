package com.umcincoum.dj.repository.postgre;

import com.umcincoum.dj.model.postgre.TbJukeboxMusic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbJukeboxMusicRepository extends CrudRepository<TbJukeboxMusic, String> {
    List<TbJukeboxMusic> findByPlaylistAndArtistContains(String playlist, String artist);
    List<TbJukeboxMusic> findByPlaylistAndArtistContainsAndMusicContains(String playlist, String artist, String music);
    List<TbJukeboxMusic> findByPlaylist(String playlist);
    List<TbJukeboxMusic> findByPlaylistAndMusicContains(String playlist, String music);
}
