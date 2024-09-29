package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.MovieDto;
import com.ravn.challenge.movies_catalog_management.dto.QuerySearchRequestDto;
import com.ravn.challenge.movies_catalog_management.dto.UpdateMovieDto;
import com.ravn.challenge.movies_catalog_management.model.Movie;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;

public interface MovieService {

    GenericRestResponse<Movie> createMovie(MovieDto movieDto);

    GenericRestResponse<Movie> updateMovie(UpdateMovieDto movieDto);

    GenericRestResponse<Movie> deleteMovie(Long id);

    GenericRestListResponse<Movie> searchMovies(QuerySearchRequestDto queryList);
}
