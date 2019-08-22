package com.umcincoum.dj.service.postgre;

import com.umcincoum.dj.model.postgre.TbJukeboxMusic;
import com.umcincoum.dj.repository.postgre.TbJukeboxMusicRepository;
import com.umcincoum.dj.service.SearchMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbJukeboxMusicService {
    @Autowired
    TbJukeboxMusicRepository tbJukeboxMusicRepository;

    @Autowired
    SearchMusicService searchMusicService;

    public void saveAll(List<TbJukeboxMusic> tbJukeboxMusic){
        tbJukeboxMusicRepository.saveAll(tbJukeboxMusic);
    }

    public void loadPlaylist(String playlist){
        saveAll(searchMusicService.getTracksFromPlaylist(playlist));
    }

    public List<TbJukeboxMusic> queryByArtist(String artist, String playlist){
        return tbJukeboxMusicRepository.findByPlaylistAndArtistContains(playlist,artist.toUpperCase());
    }

    public List<TbJukeboxMusic> queryByArtistFilterByMusic(String artist, String music, String playlist){
        return tbJukeboxMusicRepository.findByPlaylistAndArtistContainsAndMusicContains(playlist,artist.toUpperCase(), music.toUpperCase());
    }

    public List<TbJukeboxMusic> queryByPlaylist(String playlist){
        return tbJukeboxMusicRepository.findByPlaylist(playlist);
    }

    public List<TbJukeboxMusic> queryByPlaylistFilterMusic(String playlist, String music){
        return tbJukeboxMusicRepository.findByPlaylistAndMusicContains(playlist, music);
    }





}
