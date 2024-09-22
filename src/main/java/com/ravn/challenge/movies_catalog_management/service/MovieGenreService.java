package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.model.MovieGenre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieGenreService {
    void createMovieGenre(MovieGenre movieGenre);
    List<MovieGenre> findByMovieId(Long movieId);
    void deleteById(Long id);
}
