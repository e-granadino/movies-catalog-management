package com.ravn.challenge.movies_catalog_management.controller;

import com.ravn.challenge.movies_catalog_management.model.Genre;
import com.ravn.challenge.movies_catalog_management.model.MovieGenre;
import com.ravn.challenge.movies_catalog_management.service.GenreServiceImpl;
import com.ravn.challenge.movies_catalog_management.service.MovieGenreServiceImpl;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genres")
@Tag(name = "Genre Management", description = "API for managing movie's genres")
@SecurityRequirement(name = "bearerAuth")
public class GenreController {

    @Operation(summary = "List Genres", description = "List All Genres stored in the database")
    @ApiResponse(responseCode = "200", description = "Genres retrieved successfully", content = {@Content(mediaType = "application/json")})
    @GetMapping("/list")
    public GenericRestListResponse<Genre> list() {
        return genreService.listGenres();
    }

    @Operation(summary = "List Genres by Movie ID", description = "List All Genres that match with the movie id")
    @ApiResponse(responseCode = "200", description = "Genres retrieved successfully", content = {@Content(mediaType = "application/json")})
    @GetMapping("/listByMovieId")
    public GenericRestListResponse<MovieGenre> listGenresByMovieId(@RequestParam(name = "movieId") Long movieId) {
        GenericRestListResponse<MovieGenre> response = new GenericRestListResponse<>();

        List<MovieGenre> genreList = movieGenreService.findByMovieId(movieId);
        response.setRecords(genreList);
        response.setStatus(Constants.SUCCESS_RESPONSE);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("The movie's genres were retrieved successfully");

        return response;
    }

    @Autowired
    public GenreController(GenreServiceImpl genreService, MovieGenreServiceImpl movieGenreService){
        this.movieGenreService = movieGenreService;
        this.genreService = genreService;
    }

    private final GenreServiceImpl genreService;
    private final MovieGenreServiceImpl movieGenreService;
}
