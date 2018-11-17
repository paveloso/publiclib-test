package com.postscriptum.ThePubLib.repos;

import com.postscriptum.ThePubLib.entities.Genres;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepo extends CrudRepository<Genres, Long> {

    List<Genres> findGenresBy(String genreType);
}
