package com.umcincoum.dj;

import com.umcincoum.dj.model.ArtistsModel;
import com.umcincoum.dj.service.SearchMusicService;

import java.util.ArrayList;
import java.util.List;

public class SearchMusicServiceTest {

    public static void main(String[] args) {
        SearchMusicService searchMusicService = new SearchMusicService();
        List<ArtistsModel> list = new ArrayList<>();
        list = searchMusicService.getArtists("foo");
    }
}
