package com.umcincoum.dj.service.postgre;

import com.umcincoum.dj.model.postgre.TbJukeboxArtist;
import com.umcincoum.dj.repository.postgre.TbJukeboxArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbJukeboxArtistService {

    @Autowired
    TbJukeboxArtistRepository tbJukeboxArtistRepository;

    public void saveAll(List<TbJukeboxArtist> list){
        tbJukeboxArtistRepository.saveAll(list);
    }

    public List<TbJukeboxArtist> findArtistsByPlaylist(String playlist){
        return tbJukeboxArtistRepository.findByPlaylistOrderByName(playlist);
    }

    public List<TbJukeboxArtist> findByPlaylistAndArtist(String playlist, String name){
        return tbJukeboxArtistRepository.findByPlaylistAndNameContains(playlist,name);
    }
}
