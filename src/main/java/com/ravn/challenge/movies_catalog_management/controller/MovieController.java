package com.ravn.challenge.movies_catalog_management.controller;

import com.ravn.challenge.movies_catalog_management.dto.MovieDto;
import com.ravn.challenge.movies_catalog_management.dto.QuerySearchRequestDto;
import com.ravn.challenge.movies_catalog_management.dto.UpdateMovieDto;
import com.ravn.challenge.movies_catalog_management.model.Movie;
import com.ravn.challenge.movies_catalog_management.service.MovieServiceImpl;
import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/movies")
@Tag(name = "Movie Management", description = "API for managing movies")
@SecurityRequirement(name = "bearerAuth")
public class MovieController {

    @Operation(summary = "Search Movies", description = Constants.SWAGGER_MOVIE_SEARCH_DESCRIPTION)
    @ApiResponse(responseCode = "200", description = "The Search criteria returns records.", content = {@Content(schema = @Schema(implementation = GenericRestListResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "404", description = "No records were found with the search criteria.", content = {@Content(mediaType = "application/json")})
    @PostMapping("search")
    public GenericRestListResponse<Movie> searchMovies(@RequestBody QuerySearchRequestDto requestBody){
        return movieService.searchMovies(requestBody);
    }

    @Operation(summary = "Create Movie", description = "Only admin users can create movies")
    @ApiResponse(responseCode = "200", description = "The movie has been created successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "401", description = "Unauthorized, user has not permissions to create movie", content = {@Content(mediaType = "application/json")})
    @PostMapping("create")
    public GenericRestResponse<Movie> createMovie(@Valid @RequestBody MovieDto movieDto){
        return movieService.createMovie(movieDto);
    }

    @Operation(summary = "Update Movie", description = "Only admin users can update movies")
    @ApiResponse(responseCode = "200", description = "The movie has been updated successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "401", description = "Unauthorized, user has not permissions to update movie", content = {@Content(mediaType = "application/json")})
    @PutMapping("update")
    public GenericRestResponse<Movie> updateMovie(@Valid @RequestBody UpdateMovieDto movieDto){
        return movieService.updateMovie(movieDto);
    }

    @Operation(summary = "Delete Movie", description = "Only admin users can update movies")
    @ApiResponse(responseCode = "200", description = "The movie has been deleted successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "There was an issue deleting the move, It might be due the movie doesn't exist on the database", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "401", description = "Unauthorized, user has not permissions to delete movie", content = {@Content(mediaType = "application/json")})
    @DeleteMapping("delete/{id}")
    public GenericRestResponse<Movie> deleteMovie(@PathVariable(name = "id") Long id){
        return movieService.deleteMovie(id);
    }

    @Autowired
    public MovieController(MovieServiceImpl movieService){
        this.movieService = movieService;
    }

    private final MovieServiceImpl movieService;
}
