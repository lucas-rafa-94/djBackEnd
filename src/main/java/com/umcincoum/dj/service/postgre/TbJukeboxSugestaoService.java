package com.umcincoum.dj.service.postgre;

import com.umcincoum.dj.model.postgre.TbJukeboxSugestao;
import com.umcincoum.dj.repository.postgre.TbJukeboxSugestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbJukeboxSugestaoService {

    @Autowired
    TbJukeboxSugestaoRepository tbJukeboxSugestaoRepository;

    public void save(TbJukeboxSugestao tbJukeboxSugestao){
        tbJukeboxSugestaoRepository.save(tbJukeboxSugestao);
    }
}
