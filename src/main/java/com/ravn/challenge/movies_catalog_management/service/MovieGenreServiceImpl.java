package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.model.MovieGenre;
import com.ravn.challenge.movies_catalog_management.repository.MovieGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieGenreServiceImpl implements MovieGenreService{
    public void createMovieGenre(MovieGenre movieGenre) {
        movieGenreRepository.save(movieGenre);
    }

    public List<MovieGenre> findByMovieId(Long movieId) {
        return movieGenreRepository.findByMovieId(movieId);
    }

    public void deleteById(Long id) {
        movieGenreRepository.deleteById(id);
    }

    @Autowired
    public MovieGenreServiceImpl(MovieGenreRepository movieGenreRepository){
        this.movieGenreRepository = movieGenreRepository;
    }

    private final MovieGenreRepository movieGenreRepository;
}
