package com.umcincoum.dj.repository.postgre;

import com.umcincoum.dj.model.postgre.TbJukeboxSugestao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbJukeboxSugestaoRepository extends CrudRepository<TbJukeboxSugestao, String> {
}
