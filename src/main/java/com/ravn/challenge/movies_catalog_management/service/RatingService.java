package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.RatingDto;
import com.ravn.challenge.movies_catalog_management.model.Rating;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;

import java.util.List;

public interface RatingService {
    void listRatingByUserName(GenericRestListResponse<Rating> response);
    void createUserRating(RatingDto ratingDto, GenericRestResponse<Rating> response);
    void deleteUserRating(Long ratingId, GenericRestResponse<Rating> response);
    List<Rating> findByMovieId(Long movieId);
    void deleteById(Long id);
}
