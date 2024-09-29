package com.ravn.challenge.movies_catalog_management.controller;

import com.ravn.challenge.movies_catalog_management.dto.RatingDto;
import com.ravn.challenge.movies_catalog_management.model.Rating;
import com.ravn.challenge.movies_catalog_management.service.RatingServiceImpl;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestListResponse;
import com.ravn.challenge.movies_catalog_management.utils.GenericRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ratings")
@Tag(name = "Rating Management", description = "API for managing user's rating")
@SecurityRequirement(name = "bearerAuth")
public class RatingController {

    @Operation(summary = "List User Ratings", description = "Lists all ratings created by the user logged")
    @ApiResponse(responseCode = "200", description = "The ratings have been retrieved successfully", content = {@Content(mediaType = "application/json")})
    @GetMapping("listAllByUserLogged")
    public GenericRestListResponse<Rating> findByUser(){
        return ratingService.listRatingByUserName();
    }

    @Operation(summary = "Create User Rating", description = "Current User logged creates a rating for a movie")
    @ApiResponse(responseCode = "200", description = "The rating has been created successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Bad Request, the movie which user want to rate was not found on the database.", content = {@Content(mediaType = "application/json")})
    @PostMapping("create")
    public GenericRestResponse<Rating> createUserRating(@Valid @RequestBody RatingDto ratingDto){
        return ratingService.createUserRating(ratingDto);
    }

    @Operation(summary = "Delete Rating", description = "Deletes a Rating by Id")
    @ApiResponse(responseCode = "200", description = "The rating has been deleted successfully", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "400", description = "Bad Request, the rating was not found in the database", content = {@Content(mediaType = "application/json")})
    @DeleteMapping("delete/{rateId}")
    public GenericRestResponse<Rating> deleteRating(@PathVariable(name = "rateId") Long rateId){
        return ratingService.deleteUserRating(rateId);
    }

    @Autowired
    public  RatingController(RatingServiceImpl ratingService){
        this.ratingService = ratingService;
    }

    private final RatingServiceImpl ratingService;
}
