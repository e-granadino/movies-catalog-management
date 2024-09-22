package com.ravn.challenge.movies_catalog_management.repository;

import com.ravn.challenge.movies_catalog_management.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long>, JpaSpecificationExecutor<MovieGenre> {
    List<MovieGenre> findByMovieId(Long movieId);
}