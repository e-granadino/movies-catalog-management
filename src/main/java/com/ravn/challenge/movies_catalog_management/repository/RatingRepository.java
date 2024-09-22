package com.ravn.challenge.movies_catalog_management.repository;

import com.ravn.challenge.movies_catalog_management.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long>, JpaSpecificationExecutor<Rating> {
    List<Rating> findByMovieIdAndCreatedBy(Long movieId, String createdBy);
    List<Rating> findByCreatedBy(String createdBy);
    List<Rating> findByMovieId(Long movieId);
}