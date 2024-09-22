package com.ravn.challenge.movies_catalog_management.service;

import com.ravn.challenge.movies_catalog_management.dto.MovieDto;
import com.ravn.challenge.movies_catalog_management.dto.QuerySearchRequestDto;
import com.ravn.challenge.movies_catalog_management.dto.UpdateMovieDto;
import com.ravn.challenge.movies_catalog_management.model.Movie;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;

public interface MovieService {

    void createMovie(MovieDto movieDto, GenericRestResponse<Movie> response);

    void updateMovie(UpdateMovieDto movieDto, GenericRestResponse<Movie> response);

    void deleteMovie(Long id, GenericRestResponse<Movie> response);

    void searchMovies(QuerySearchRequestDto queryList, GenericRestListResponse<Movie> response);
}
